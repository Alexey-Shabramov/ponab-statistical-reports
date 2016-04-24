package com.uz.laboratory.statistical.dict;

public class Constants {

    /**
     * FXML path's
     */
    public static final String MAIN_FXML_PATH = "/fxml/mainAnchor.fxml";
    public static final String PONAB_REMARK_EDIT_MODAL = "/fxml/modal/edit/remark/ponabRemarkEditModal.fxml";
    public static final String INSPECTION_EDIT_MODAL = "/fxml/modal/edit/shedule/inspectionTripEditModal.fxml";
    public static final String PONAB_DEVICE_EDIT_MODAL = "/fxml/modal/edit/ponab/ponabDeviceEditModal.fxml";
    public static final String ALS_DEVICE_EDIT_MODAL = "/fxml/modal/edit/als/alsDeviceEditModal.fxml";
    public static final String PLANNED_TRIP_MODAL = "/fxml/modal/edit/shedule/plannedTripEditModal.fxml";

    public static final String VALIDATE_ALS_DELETION_MODAL = "/fxml/modal/delete/validateAlsDeviceDeletion.fxml";
    public static final String VALIDATE_PONAB_DELETION_MODAL = "/fxml/modal/delete/validatePonabDeviceDeletion.fxml";
    public static final String VALIDATE_INSPECTION_TRIP_DELETION_MODAL = "/fxml/modal/delete/validateInspectionTripDeletion.fxml";
    public static final String VALIDATE_PLANNED_TRIP_MODAL = "/fxml/modal/delete/validatePlannedTripDeletion.fxml";
    public static final String VALIDATE_REMARK_DELETION_MODAL = "/fxml/modal/delete/validateRemarkDeletion.fxml";

    public static final String REMARK_VIEW_MODAL = "/fxml/modal/view/remarkViewModal.fxml";

    /**
     * Title names
     */
    public static final String MAIN_TITLE = "Статистическая отчетность по вагон-лаборатории ЮЖД";
    public static final String REMARK_EDIT_MODAL_TITLE = "Редактирование замечания ПАВПБ";
    public static final String REMARK_DELETION_MODAL_TITLE = "Удаление замечания";

    /**
     * Vagon-laboratories names
     */
    public static final String SOUTHERN_LABORATORY = "Южная";
    public static final String ODESSA_LABORATORY = "Одесская";
    public static final String SOUTHWEST_LABORATORY = "Югозападная";
    public static final String DONETSK_LABORATORY = "Донецкая";
    public static final String PRIDNEPROVSKA_LABORATORY = "Приднепровская";

    /**
     * Type of movement
     */
    public static final String EVEN = "Ч";
    public static final String UNEVEN = "Н";

    /**
     * Devices names
     */
    public static final String ASKD = "АСДК-Б";
    public static final String PONAB = "ПОНАБ";
    public static final String DISK = "ДИСК-Б";
    public static final String KTSM = "КТСМ-02";

    /**
     * Validatorы errorы messages
     */
    public static final String SECTOR_IS_NOT_CHOSEN = "Проверяемый участок не выбран.";
    public static final String DATE_FORMAT = "dd.MM.YYYY";
    public static final String YEAR_VALUES_IS_NOT_SET = "Значение года не установлено. Проверьте Ваши данные.";
    public static final String MONTH_VALUES_IS_NOT_SET = "Значение месяца не установлено.";

    /**
     * Statistical part - Tableview columns names
     */
    public static final String FIRST_COLUMN = "Объект";

    /**
     * Alert titles and basic messages
     */
    public static final String ERROR_TITLE = "ПРОИЗОШЛА ОШИБКА!";
    public static final String ERROR_HEADER = "Данное сообщение появляеться при наличии неточностей во введенной информации.";

    public static final String REMARK_REPEATABLE_TRUE = "+";
    public static final String REMARK_REPEATABLE_FALSE = "-";
    public static final String SPEACH_INFORMATOR_TRUE = "+";

    public static final String SPEACH_INFORMATOR_FALSE = "-";

    /**
     * Popup menu liks data
     */
    public static final String VIEW_INFO = "Просмотр";
    public static final String EDIT_INFO = "Редактировать";
    public static final String DELETE_INFO = "Удалить";
    public static final String EDIT_PONAB_REMARK_NULL_STAGE = "Вы не установили значение перегона для данного замечания.";
    public static final String EDIT_PONAB_REMARK_NULL_SECTOR = "Вы не установили значение участка для данного замечания.";
    public static final String EDIT_PONAB_REPEAT_NULL = "Не выбран тип замечания (повторный или нет).";
    public static final String EDIT_PONAB_INSPECTION_NULL = "Не установлена проверочная поездка.";
    public static final String EDIT_PONAB_REMARK_FORMATION_DATE_NULL = "Не установлена дата оформления замечания.";
    public static final String EDIT_PONAB_SYSTEM_IS_NOT_SET = "Не выбрана система к данному замечанию.";
}
