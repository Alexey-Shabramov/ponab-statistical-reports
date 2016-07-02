package com.uz.laboratory.statistical.util;


import com.uz.laboratory.statistical.dict.Constants;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.*;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class GitUtil {
    private static final RefSpec REF_SPEC = new RefSpec(Constants.GIT_REF_SPEC);
    private static final RefSpec REMOTE_REF_SPEC = new RefSpec(Constants.GIT_REMOTE_REF_SPEC);
    private static final CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(Constants.GIT_REMOTE_USER_NAME, Constants.GIT_REMOTE_PASSWORD);
    private static Git git;

    @PostConstruct
    public void initGitRepository() throws Exception {
        File file = new File(Constants.GIT_HSQLDB_MAIN_FILE_PATH);
        FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
        repositoryBuilder.addCeilingDirectory(file);
        repositoryBuilder.findGitDir(file);
        if (repositoryBuilder.getGitDir() == null) {
            git = Git.init().setDirectory(file.getParentFile()).call();
            StoredConfig config = git.getRepository().getConfig();
            config.setString("remote", "origin", "url", Constants.GIT_HSQLDB_REMOTE_REPOSTITORY_URL);
            RemoteConfig remoteConfig = new RemoteConfig(config, "remote");
            remoteConfig.addURI(new URIish(git.getRepository().getDirectory().toURI().toURL()));
            remoteConfig.update(config);
            RemoteConfig originConfig = new RemoteConfig(config, "origin");
            originConfig.addFetchRefSpec(REMOTE_REF_SPEC);
            originConfig.update(config);
            config.save();
        } else {
            git = new Git(repositoryBuilder.build());
        }
        addUntrackedFiles(git.status().call().getUntracked(), git.getRepository());
    }

    public void commitAndPush() throws Exception {
        addUntrackedFiles(git.status().call().getUntracked(), git.getRepository());
        addChangedFiles(git.status().call().getModified(), git.getRepository());
        git.commit().setMessage("Update").call();
        git.push().setRemote("origin")
                .setCredentialsProvider(credentialsProvider)
                .setRefSpecs(REF_SPEC)
                .call();
    }

    public void pullAndMerge() throws GitAPIException, IOException {
        git.pull().call();
    }

    private void addUntrackedFiles(Collection<String> notTracked, Repository repository) throws Exception {
        if (notTracked == null || notTracked.size() == 0)
            return;
        AddCommand addCommand = git.add();
        for (String path : notTracked) {
            addCommand.addFilepattern(path);
        }
        addCommand.call();
    }

    private void addChangedFiles(Collection<String> changed, Repository repository) throws GitAPIException {
        if (changed == null || changed.size() == 0)
            return;
        AddCommand addCommand = git.add();
        for (String path : changed) {
            addCommand.addFilepattern(path);
        }
        addCommand.call();
    }
}
