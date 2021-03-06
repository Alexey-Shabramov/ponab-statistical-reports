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

    public static final String STATION_EDIT_OR_CREATE_MODAL = "/fxml/modal/edit/location/stationEditOrCreateModal.fxml";
    public static final String DISTANCE_EDIT_OR_CREATE_MODAL = "/fxml/modal/edit/location/communicationDistanceEditOrCreateModal.fxml";
    public static final String SECTOR_EDIT_OR_CREATE_MODAL = "/fxml/modal/edit/location/sectorEditOrCreateModal.fxml";

    public static final String LABORATORY_EDIT_OR_CREATE_MODAL = "/fxml/modal/edit/shedule/vagonLaboratoryEditOrCreateModal.fxml";
    public static final String STAGE_EDIT_OR_CREATE_MODAL = "/fxml/modal/edit/location/stageCreateOrEditModal.fxml";

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
     * Directions of bean (by partly)
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
     * FXML - stationEditOrCreateModal.fxml
     */
    public static final String STATION_MODAL_TITLE = "Редактирование/Добавление станции";
    public static final String STATION_MODAL_NAME_NULL = "Название станции пустое. Повторите Ваш ввод.";

    /**
     * FXML - sectorEditOrCreateModal.fxml
     */
    public static final String SECTOR_MODAL_TITLE = "Редактирование/Добавление участка";
    public static final String SECTOR_MODAL_NAME_NULL = "Название станции пустое. Повторите Ваш ввод.";
    public static final String SECTOR_EDIT_FIRST_STATION_NULL = "Первая станция не выбрана.";
    public static final String SECTOR_EDIT_LAST_STATION_NULL = "Последняя станция не выбрана.";
    public static final String SECTOR_EDIT_STAGE_LIST_NULL = "Не выбраны перегоны для данного участка.";

    /**
     * FXML - communicationDistanceEditOrCreateModal.fxml
     */
    public static final String DISTANCE_MODAL_TITLE = "Редактирование/Добавление ШЧ";
    public static final String DISTANCE_MODAL_NAME_NULL = "Номер ШЧ не выбран. Повторите Ваш выбор.";

    /**
     * FXML - vagonLaboratoryEditOrCreateModal.fxml
     */
    public static final String LABORATORY_MODAL_TITLE = "Редактирование/Добавление вагон-лаборатории";
    public static final String LABORATORY_MODAL_NAME_NULL = "Номер ШЧ не выбран. Повторите Ваш выбор.";

    /**
     * FXML - stageCreateOrEditModal.fxml
     */
    public static final String STAGE_MODAL_TITLE = "Редактирование/Добавление перегона";

    /**
     * Apache POI - titles
     */
    public static final String REMARK_REPORT_SHEET_NAME = "Сравнительная таблица";
    public static final String TEXTFIELD_IS_EMPTY = "Название файла пустое. Повторите ввод.";

    public static final String SHEDULE_ERROR_TYPE_IS_NOT_SET = "Не выбран тип проверок.";

    /**
     * FXML - tripViewModal.fxml
     */
    public static final String TRIP_VIEW_TITLE = "Редактирование/Создание поездки";

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
     * FXML - stageCreateOrEditModal.fxml
     */
    public static final String STAGE_EDIT_FIRST_STATION_NULL = "Первая станция не выбрана.";
    public static final String STAGE_EDIT_SECOND_STATION_NULL = "Вторая станция не выбрана.";
    public static final String STAGE_EDIT_COMMUNICATION_DISTANCE_NULL = "Не выбрано ШЧ.";


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

    public static final String PONAB_DEVICE_EDIT_NOT_SET = "Не выбрана система для редактирования.";
    public static final String ALS_DEVICE_EDIT_NOT_SET = "Не выбрана рельсовая цепь для редактирования.";
    public static final String STATION_EDIT_NOT_SET = "Не выбрана станция для редактирования.";
    public static final String COMMUNICATION_DISTANCE_EDIT_NOT_SET = "Не выбрано ШЧ для редактирования.";
    public static final String VAGON_LABORATORY_EDIT_NAME_NULL = "Не выбран идентификатор/имя для вагон лаборатории.";
    public static final String STAGE_EDIT_NULL = "Не выбран перегон для редактирования.";
    public static final String SECTOR_EDIT_NULL = "Не выбран участок для редактирования.";

    /**
     * FXML - settings.fxml messages
     */
    public static final String SETTINGS_SCRIPT_FILE_PATH_EMPTY = "Путь к файлу - пуст.";

    /**
     * Git - repository configurations and error messages
     */
    public static final String GIT_HSQLDB_MAIN_FILE_PATH = "/database/ponab_statistical_reports.script";
    public static final String GIT_HSQLDB_REMOTE_REPOSTITORY_URL = "https://github.com/Alexey-Shabramov/ponab-statistical-hsqldb-base.git";
    public static final String GIT_REF_SPEC = "refs/heads/master:refs/heads/master";
    public static final String GIT_REMOTE_REF_SPEC = "+refs/heads/*:refs/remotes/origin/*";

    public static final String GIT_REMOTE_USER_NAME = "Alexey-Shabramov";
    public static final String GIT_REMOTE_PASSWORD = "123krsmKRSM123oo";
    public static final String DATABASE_SCRIPT_DUMP_NULL = "Отсутствует файл обновления для базы данных";

    public static final String INTERNET_IS_NOT_AVAILABLE = "Отсутствует интернет соединение. Проверьте соединение и повторите попытку.";

    public static final String TRIP_NULL = "Не выбрана поездка.";

    /**
     * Tables for base devices (ASDK, PONAB) options name
     */
    public static final String TABLE_100 = "100";
    public static final String TABLE_120 = "120";
    public static final String TABLE_140 = "140";
    public static final String TABLE_160 = "160";

    /**
     * Devices names
     */
    public static final String ASKD = "АСДК-Б";
    public static final String PONAB = "ПОНАБ";
    public static final String DISK = "ДИСК-Б";
    public static final String KTSM = "КТСМ-02";

    /**
     * Check-thread names
     */
    public static final String CHECK_RIGHT_SENSOR = "";
    public static final String CHECK_LEFT_SENSOR = "";

    /**
     * Sensors physical names
     */
    public static final String ADAPTER = "adapter.default.address";
    public static final String ADAPTER_PORT = "adapter.default.port";
    public static final String EVEN_RIGHT_IRON_SENSOR = "even.right_iron.sensor.address";
    public static final String EVEN_LEFT_IRON_SENSOR = "even.left_iron.sensor.address";
    public static final String UNEVEN_LEFT_IRON_SENSOR = "odd.left_iron.sensor.address";
    public static final String UNEVEN_RIGHT_IRON_SENSOR = "odd.right_iron.sensor.address";
    public static final String EVEN_CONTROL_PORT_ADDRESS = "even.control.port.address";
    public static final String UNEVEN_CONTROL_PORT_ADDRESS = "odd.control.port.address";
    public static final String TEMPERATURE_LEFT_SENSOR = "thermal.left.sensor.address";
    public static final String TEMPERATURE_RIGHT_SENSOR = "thermal.right.sensor.address";
    public static final String ALERT_WARMING_VALUES_IS_EMPTY = "Параметры температуры пустые. (Введите хотябы один параметр для одной из сторон).";
    public static final String ALERT_CURRENT_SETTINGS_CONTROL_PORT_NOT_WORKING = "Отсутствует значение контрольного порта для управления нагревом утюгов либо значение не верное.(Проверьте настройки).";
    public static final String ALERT_MOVEMENT_IS_NOT_SET = "Не задано текущее направление движения. Повторите ввод.";
    public static final String OPTION_IS_NOT_SET = "Не задана настройка для нагрева.";

    /**
     * Logger messages
     */
    public static final String SENSOR_CHECK_MESSAGE = "Статус: Доступен;  Проверка: Исправен;  Температура: ";
    public static final String WRONG_SENSOR_ID_INSERTED = "Вы ввели неверный ID или такого датчика не существует! \n";
    public static final String ERROR_READING_DEVICE = "При чтении устройства возникла ошибка! \n";
    public static final String PORTS_ID_SAVED = "Адреса портов сохранены. \n";
    public static final String TEMPERATURE_SENSORS_ID_SAVED = "Адреса датчиков температуры внешней среды сохранены. \n";
    public static final String SENSORS_NOT_FOUND = "Данные датчики не обнаружены! \n";
    public static final String TEMPERATURE_IS = "Температура равна: ";

    /**
     * FXML CSS
     */
    public static final String FX_TEXT_BACKGROUND_GREEN = "-fx-background-color: green;";
    public static final String FX_TEXT_BACKGROUND_YELLOW = "-fx-background-color: yellow;";
    public static final String FX_TEXT_BACKGROUND_RED = "-fx-background-color: red;";

    public static final String AUTOFINDING_DATA = "Производиться поиск данных из текущих настроек...\n";
    public static final String AUTOFINDING_DATA_IS_ADDED = "Данные по текущим настройкам добавлены.\n";
    public static final String ADAPTER_IS_NOT_WORKING = "Адаптер не работает!";
    public static final String SENSOR_IS_NOT_BLANK = "Сенсор исправен и не null!";
}
