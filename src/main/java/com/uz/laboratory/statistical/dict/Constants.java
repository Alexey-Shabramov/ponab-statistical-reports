package com.uz.laboratory.statistical.dict;

public class Constants {
    /**
     * Dozer bean mapper mappings names
     */
    public static final String PONAB_DEVICE_TO_DTO = "ponabDeviceToDto";
    public static final String ALS_DEVICE_TO_DTO = "alsDeviceToDto";
    public static final String INSPECTION_TRIP_TO_DTO = "inspectionTripToDto";

    public static final String CLEAN_PONAB_REMARK_DTO = "cleanPonabRemarkDto";
    public static final String CLEAN_ALS_REMARK_DTO = "cleanAlsRemarkDto";
    public static final String CLEAN_PONAB_DEVICE_DTO = "cleanPonabDeviceDto";
    public static final String CLEAN_ALS_DEVICE_DTO = "cleanAlsDeviceDto";
    public static final String CLEAN_REMARK_TABLE_SAVE_DTO = "cleanRemarkTableSaveDto";
    public static final String CLEAN_INSPECTION_TRIP_DTO = "cleanInspectionTripDto";

    /**
     * FXML - all fxml path's
     */
    public static final String MAIN_FXML_PATH = "/fxml/mainAnchor.fxml";
    public static final String VALIDATE_ENTITY_DELETION_MODAL = "/fxml/modal/delete/validateEntityDeletion.fxml";

    public static final String PONAB_REMARK_EDIT_MODAL = "/fxml/modal/edit/remark/ponabRemarkEditModal.fxml";
    public static final String ALS_REMARK_EDIT_MODAL = "/fxml/modal/edit/remark/alsRemarkEditModal.fxml";

    public static final String INSPECTION_EDIT_MODAL = "/fxml/modal/edit/shedule/tripEditModal.fxml";
    public static final String PONAB_DEVICE_EDIT_MODAL = "/fxml/modal/edit/ponab/ponabDeviceEditModal.fxml";
    public static final String PONAB_DEVICE_VIEW_MODAL = "/fxml/modal/view/ponab/ponabDeviceViewModal.fxml";

    public static final String ALS_DEVICE_VIEW_MODAL = "/fxml/modal/view/als/alsDeviceViewModal.fxml";
    public static final String ALS_DEVICE_EDIT_MODAL = "/fxml/modal/edit/als/alsDeviceEditModal.fxml";
    public static final String TRIP_EDIT_MODAL = "/fxml/modal/edit/shedule/tripEditModal.fxml";

    public static final String REMARK_TABLE_SAVE_MODAL = "/fxml/modal/save/saveRemarkTable.fxml";

    public static final String PONAB_REMARK_VIEW_MODAL = "/fxml/modal/view/ponab/ponabRemarkViewModal.fxml";
    public static final String ALS_REMARK_VIEW_MODAL = "/fxml/modal/view/als/alsRemarkViewModal.fxml";
    public static final String INSPECTION_TRIP_VIEW_MODAL = "/fxml/modal/view/shedule/tripViewModal.fxml";



    /**
     * FXML mainAnchor.fxml - Title names
     */
    public static final String MAIN_TITLE = "Статистическая отчетность по вагон-лаборатории ЮЖД";
    public static final String REMARK_PONAB_EDIT_MODAL_TITLE = "Редактирование замечания ПАВПБ";
    public static final String REMARK_ALS_EDIT_MODAL_TITLE = "Редактирование замечания АЛС";
    public static final String REMARK_ALS_VIEW_MODAL_TITLE = "Просмотр замечания АЛС";
    public static final String REMARK_PONAB_VIEW_MODAL_TITLE = "Просмотр замечания ПАВПБ";

    public static final String ENTITY_DELETION_MODAL_TITLE = "Удаление выбранного объекта";
    public static final String REMARK_TABLE_SAVE_MODAL_TITLE = "Сохранение таблицы в EXCEL";

    /**
     * Directions of movement (by partly)
     */
    public static final String EVEN = "Ч";
    public static final String UNEVEN = "Н";

    /**
     * Validators error-messages
     */
    public static final String SECTOR_IS_NOT_CHOSEN = "Проверяемый участок не выбран.";
    public static final String DATE_FORMAT = "dd.MM.YYYY";

    /**
     * Remark basic repeatable value (true of false)
     */
    public static final String REMARK_REPEATABLE_TRUE = "+";
    public static final String REMARK_REPEATABLE_FALSE = "-";

    /**
     * Ponab system speach informator values
     */
    public static final String SPEACH_INFORMATOR_TRUE = "+";
    public static final String SPEACH_INFORMATOR_FALSE = "-";

    /**
     * Popup menu liks data
     */
    public static final String POPUP_MENU_VIEW_INFO = "Просмотр";
    public static final String POPUP_MENU_EDIT_INFO = "Редактировать";
    public static final String POPUP_MENU_PRINT_TABLE_VIEW = "Распечатать таблицу";
    public static final String POPUP_MENU_SAVE_TABLE_VIEW = "Сохранить таблицу";
    public static final String SAFETY_SPACE = "";
    public static final String POPUP_MENU_DELETE_INFO = "Удалить";

    /**
     * FXML - Alert basic messages
     */
    public static final String ERROR_TITLE = "ПРОИЗОШЛА ОШИБКА!";
    public static final String ERROR_HEADER = "Данное сообщение появляеться при наличии неточностей во введенной информации.";

    /**
     * FXML - ponabDeviceEditModal.fxml alert messages
     */
    public static final String EDIT_REMARK_NULL_STAGE = "Вы не установили значение перегона для данного замечания.";
    public static final String EDIT_REMARK_NULL_SECTOR = "Вы не установили значение участка для данного замечания.";
    public static final String EDIT_REPEAT_NULL = "Не выбран тип замечания (повторный или нет).";
    public static final String EDIT_INSPECTION_NULL = "Не установлена проверочная поездка.";
    public static final String EDIT_REMARK_FORMATION_DATE_NULL = "Не установлена дата оформления замечания.";
    public static final String EDIT_SYSTEM_IS_NOT_SET = "Не выбрана система к данному замечанию.";
    public static final String DEVICES_TYPE_IS_NOT_SET = "Тип устройств не выбран. (Для поиска - обязательно выбрать тип устройств).";


    /**
     * FXML - ponabRemarkViewModal.fxml GUI text
     */
    public static final String REMARK_VIEW_TREE_TITLE = "Замечание №";
    public static final String REMARK_VIEW_DATE_TITLE = "Дата замечания:";
    public static final String REMARK_VIEW_SECTOR_TITLE = "Участок:";
    public static final String REMARK_VIEW_STAGE_TITLE = "Перегон:";
    public static final String REMARK_VIEW_NOTE_TITLE = "Описание замечания:";
    public static final String REMARK_VIEW_REPEAT_TITLE = "Повторное:";
    public static final String REMARK_VIEW_REPEAT_TRUE = "Да";
    public static final String REMARK_VIEW_REPEAT_FALSE = "Нет";
    public static final String REMARK_VIEW_SYSTEM_DIRECTION = "Направление движения:";
    public static final String REMARK_VIEW_SYSTEM_DIRECTION_EVEN = "Четное";
    public static final String REMARK_VIEW_SYSTEM_DIRECTION_UNEVEN = "Нечетное";

    public static final String REMARK_VIEW_SYSTEM_TITLE = "Система проверки букс:";
    public static final String REMARK_VIEW_SYSTEM_NAME = "Название системы";
    public static final String REMARK_VIEW_SYSTEM_OPTION = "Настройка";
    public static final String REMARK_VIEW_SYSTEM_INFORMER = "Речевой информатор";
    public static final String REMARK_VIEW_SYSTEM_INFORMER_TRUE = "Есть";
    public static final String REMARK_VIEW_SYSTEM_INFORMER_FALSE = "Нет";

    public static final String REMARK_VIEW_STAGE_OR_STATION_TITLE = "Перегон / Станция:";
    public static final String REMARK_VIEW_TRACK_CIRCUIT_NAME = "Название рельсовой цепи";
    public static final String REMARK_VIEW_ALS_TRACK_CIRCUIT_TITLE = "Рельсовая цепь:";
    public static final String REMARK_TRACK_CIRCUIT_TYPE = "Тип рельсовой цепи";
    public static final String STATIONAL_TRACK_CIRCUIT = "Станционная";
    public static final String STAGE_TRACK_CIRCUIT = "Перегонная";

    /**
     * FXML - ponabDeviceViewModal.fxml
     */
    public static final String PONAB_DEVICE_VIEW_TITLE = "Просмотр выбранного устройства";
    public static final String PONAB_DEVICE_NUMBER = "Система №";
    public static final String PONAB_DEVICE_STAGE_TITLE = "Перегон:";
    public static final String PONAB_DEVICE_TITLE = "Название системы:";
    public static final String PONAB_DEVICE_OPTION = " Настройка";
    public static final String PONAB_DEVICE_LOCATION = "Расположение:";
    public static final String PONAB_DEVICE_DIRECTION_OF_MOVEMENT = "Направление движения:";
    public static final String PONAB_DEVICE_SPEACH_INFORMER = "Наличие РИ:";
    public static final String PONAB_DEVICE_SPEACH_INFORMER_TRUE = "Есть";
    public static final String PONAB_DEVICE_SPEACH_INFORMER_FALSE = "Отсутствует";

    /**
     * FXML - ponabDevicEditModal.fxml
     */
    public static final String PONAB_DEVICE_EDIT_TITLE = "Редактирование выбранной системы";
    public static final String PONAB_DEVICE_STAGE_NULL = "Не выбран перегон.";
    public static final String PONAB_DEVICE_OPTION_NULL = "Не выбрана настройка для данного устройства.";
    public static final String PONAB_DEVICE_SYSTEM_TYPE_NULL = "Не выбран тип системы.";
    public static final String PONAB_DEVICE_DIRECTION_OF_MOVEMENT_NULL = "Не выбрано направление движения.";
    public static final String PONAB_DEVICE_SPEACH_INFORMER_NULL = "Не выбран РИ (укажите есть или нет).";
    public static final String PONAB_DEVICE_LOCATION_NULL = "Не указанно местоположение устройства.";

    /**
     * FXML - alsDeviceViewModal.fxml
     */
    public static final String ALS_DEVICE_VIEW_TITLE = "Просмотр выбранной рельсовой цепи";
    public static final String ALS_DEVICE_NAME = "Рельсовая цепь - ";
    public static final String ALS_DEVICE_STAGE_OR_STATION = "Перегон / Станция:";
    public static final String ALS_DEVICE_TITLE = "Название рельсовой цепи:";
    public static final String ALS_DEVICE_TYPE = "Тип рельсовой цепи:";
    public static final String ALS_DEVICE_DIRECTION_OF_MOVEMENT = "Направление движения:";
    public static final String ALS_DEVICE_PICKET = "Пикет:";
    public static final String ALS_DEVICE_STAGE_OR_STATION_NULL = "Не выбран перегон/станция. Проверьте введенные данные.";
    public static final String ALS_DEVICE_SECTOR = "Не выбран участок.";
    public static final String ALS_DEVICE_DIRECTION_OF_MOVEMENT_NULL = "Не выбрано направление движения.";
    public static final String ALS_DEVICE_TYPE_NULL = "Не выбран тип рельсовой цепи.";
    public static final String ALS_DEVICE_NAME_NULL = "Не указанно имя рельсовой цепи.";


    /**
     * FXML - saveRemarkTable.fxml
     */
    public static final String REMARK_TABLE_SAVE_EMPTY_PATH = "Путь к файлу - пуст.";
    public static final String REMARK_TABLE_FOLDER_NOT_EXISTS = "Папки с текущим адресом не существует.";

    /**
     * FXML -
     */
    public static final String ALS_DEVICE_EDIT_TITLE = "Редактирование выбранной рельсовой цепи";



    /**
     * Apache POI - titles
     */
    public static final String REMARK_REPORT_SHEET_NAME = "Сравнительная таблица";
    public static final String TEXTFIELD_IS_EMPTY = "Название файла пустое. Повторите ввод.";

    public static final String SHEDULE_ERROR_TYPE_IS_NOT_SET = "Не выбран тип проверок.";


    /**
     * FXML - tripViewModal.fxml
     */
    public static final String TRIP_VIEW_TITLE = "Редактирование выбранной поездки";

    /**
     * FXML - tripEditModal.fxml
     */
    public static final String TRIP_EDIT_SECTOR_IS_NULL = "Участок проведения проверки не выбран.";
    public static final String TRIP_EDIT_DATE_IS_NULL = "Не выбрана примерная дата проведения проверки.";
    public static final String TRIP_EDIT_VAGON_IS_NULL = "Не выбрана вагон-лаборатория с которого производиться проверка.";
    public static final String TRIP_EDIT_TYPE_IS_NULL = "Не выбран тип проверки.";

    /**
     * FXML - tripViewModal.fxml
     */
    public static final String TRIP_VIEW_MODAL_TITLE = "Просмотр выбранной поездки";
    public static final String TRIP_VIEW_TRIP_ID = "Поездка №";
    public static final String TRIP_VIEW_SECTOR_TITLE = "Участок:";
    public static final String TRIP_VIEW_VAGON_TITLE = "Вагон-лаборатория:";
    public static final String TRIP_VIEW_DATE = "Дата:";
    public static final String TRIP_VIEW_TYPE = "Тип поездки:";

    /**
     * New remark creation messages
     */
    public static final String REMARK_CREATION_TRIP_NULL = "Не указана проверка для текущего замечания.";
    public static final String REMARK_CREATION_DATE_NULL = "Не указана дата фиксирования замечания.";
    public static final String REMARK_CREATION_REPEAT_NULL = "Не указана тип замечания - повторное или нет.";
    public static final String REMARK_CREATION_SYSTEM_NULL = "Не выбрано устройство, к которому закреплять данное замечание.";
    public static final String REMARK_CREATION_DIRECTION_OF_MOVEMENT_NULL = "Не выбрано направление движения.";
    public static final String REMARK_PONAB_CREATION_SUCCESS = "Новое замечание ПАВПБ было добавлено в базу замечаний.";
    public static final String REMARK_ALS_CREATION_SUCCESS = "Новое замечание АЛС было добавлено в базу замечаний.";

    public static final String OPERATION_SUCCESSFULL_TITLE = "Операция удачно завершена.";
    public static final String OPERATION_SUCCESSFULL_HEADER = "Ваше последнее действие завершено успешно.";



}
