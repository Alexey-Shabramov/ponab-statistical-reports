package com.uz.laboratory.statistical.util.fx;

import com.uz.laboratory.statistical.controller.EditDataBaseController;
import com.uz.laboratory.statistical.controller.als.AlsDevicesController;
import com.uz.laboratory.statistical.controller.ponab.PonabDevicesController;
import com.uz.laboratory.statistical.controller.remark.RemarkStatisticsController;
import com.uz.laboratory.statistical.controller.shedule.SheduleController;
import com.uz.laboratory.statistical.util.InitComboBoxesUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateGuiUtil {
    @Autowired
    private InitComboBoxesUtil initComboBoxesUtil;
    @Autowired
    private SheduleController sheduleController;
    @Autowired
    private AlsDevicesController alsDevicesController;
    @Autowired
    private PonabDevicesController ponabDevicesController;
    @Autowired
    private EditDataBaseController editDataBaseController;
    @Autowired
    private RemarkStatisticsController remarkStatisticsController;

    public void updateAllGUI() {
        initComboBoxesUtil.initComboBoxes();
        remarkStatisticsController.updateGui();
        editDataBaseController.updateGui();
        ponabDevicesController.updateGui();
        alsDevicesController.updateGui();
        sheduleController.updateGui();
    }
}
