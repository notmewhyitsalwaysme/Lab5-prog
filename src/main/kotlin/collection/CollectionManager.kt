package collection

import mu.KotlinLogging

import models.HumanBeing
import java.time.LocalDate
import java.util.TreeSet
import java.util.UUID

private val logger = KotlinLogging.logger {}

/**
 * Менеджер коллекции объектов [HumanBeing].
 *
 * Хранит элементы в [TreeSet].
 *
 * @property initDate дата инициализации коллекции
 */
class CollectionManager {

    private val collection: TreeSet<HumanBeing> = TreeSet()
    val initDate: LocalDate = LocalDate.now()

    /**
     * Добавляет элемент в коллекцию.
     *
     * @param humanBeing элемент для добавления
     * @return true - если элемент был добавлен, false - если уже существует
     */
    fun add(humanBeing: HumanBeing): Boolean {
        logger.info { "[COMMANDS] {ADD} Создан элемент ${humanBeing.id}" }
        return collection.add(humanBeing)
    }

    /**
     * Обновляет элемент с указанным [id].
     * Удаляет старый элемент и добавляет новый с тем же id,
     *
     * @param id UUID элемента для обновления
     * @param updated новые данные (id и creationDate будут сохранены от старого)
     * @return true если элемент найден и обновлён
     */
    fun update(id: UUID, updated: HumanBeing): Boolean {
        val old = getById(id) ?: return false
        collection.remove(old)
        // Сохраняем оригинальные id и creationDate
        val replaced = updated.copy(id = old.id, creationDate = old.creationDate)
        logger.info { "[COMMANDS] {UPDATE} Обновлен элемент ${old.id} от ${old.creationDate}" }
        return collection.add(replaced)
    }

    /**
     * Удаляет элемент по его [id].
     *
     * @param id UUID элемента для удаления
     * @return true если элемент найден и удалён
     */
    fun removeById(id: UUID): Boolean {
        val target = getById(id) ?: return false
        logger.info { "[COMMANDS] {REMOVE_BY_ID} Удалён элемент ${target.id} от ${target.creationDate}" }
        return collection.remove(target)
    }

    /**
     * Очищает коллекцию.
     */
    fun clear() {
        collection.clear()
        logger.info { "[COMMANDS] {CLEAR} Коллекция очищена" }
    }

    /**
     * Возвращает копию коллекции в виде [TreeSet].
     */
    fun getAll(): TreeSet<HumanBeing> {
        logger.info { "[COMMANDS] {GET_ALL} Возвращена коллекция" }
        return TreeSet(collection)
    }

    /**
     * Ищет элемент по [id].
     *
     * @param id UUID искомого элемента
     * @return элемент или null если не найден
     */
    fun getById(id: UUID): HumanBeing? {
        logger.info { "[COMMANDS] {GET_BY_ID} поиск элемента по id: $id" }
        return collection.firstOrNull { it.id == id }
    }

    /**
     * Возвращает количество элементов в коллекции.
     */
    fun size(): Int {
        logger.info { "[COMMANDS] {SIZE} Получен размер коллекции" }
        return collection.size
    }

    /**
     * Проверяет, пуста ли коллекция.
     */
    fun isEmpty(): Boolean {
        logger.info { "[COMMANDS] {IS_EMPTY} Проверка наличия элементов в коллекции" }
        return collection.isEmpty()
    }

    /**
     * Возвращает наибольший элемент коллекции.
     */
    fun getMax(): HumanBeing? {
        logger.info { "[COMMANDS] {GET_MAX} Получение максимального элемента коллекции" }
        return if (collection.isEmpty()) null else collection.last()
    }

    /**
     * Возвращает наименьший элемент коллекции.
     */
    fun getMin(): HumanBeing? {
        logger.info { "[COMMANDS] {GET_MIN} Получение минимального элемента коллекции" }
        return if (collection.isEmpty()) null else collection.first()}

    /**
     * Возвращает сумму значений поля [HumanBeing.minutesOfWaiting] для всех элементов.
     */
    fun sumOfMinutesOfWaiting(): Double {
        logger.info { "[COMMANDS] {SUM_MIN_WAIT} Получение минимального элемента коллекции по времени ожидания" }
        return collection.sumOf { it.minutesOfWaiting.toDouble() }
    }

    /**
     * Возвращает любой элемент с минимальным значением поля [HumanBeing.name].
     */
    fun minByName(): HumanBeing? {
        logger.info { "[COMMANDS] {MIN_BY_NAME} Получение минимального элемента коллекции по имени" }
        return collection.minByOrNull { it.name }
    }

    /**
     * Возвращает список значений [HumanBeing.minutesOfWaiting] в порядке убывания.
     */
    fun getMinutesOfWaitingDescending(): List<Float> {
        logger.info { "[COMMANDS] {GET_MIN_WAIT_DESC} Получение списка элементов коллекции по времени ожидания" }
        return collection.map { it.minutesOfWaiting }.sortedDescending()
    }


    /**
     * Загружает список элементов в коллекцию (вызывается при старте из FileManager).
     * Очищает текущую коллекцию перед загрузкой.
     *
     * @param items список элементов для загрузки
     */
    fun loadFromFile(items: List<HumanBeing>) {
        collection.clear()
        items.forEach { collection.add(it) }
        logger.info { "[COMMANDS] {LOAD_FROM_FILE} Загрузка коллекции из файла" }
    }

    /**
     * Возвращает ну очень красивую строку с информацией о коллекции для команды `info`.
     */
    fun getInfo(): String {
        logger.info { "[COMMANDS] {GET_INFO} Получение информации о коллекции" }
        return """
        |Тип коллекции : ${collection::class.simpleName}
        |Тип элементов : ${HumanBeing::class.simpleName}
        |Дата инициал. : $initDate
        |Кол-во элемен.: ${collection.size}
    """.trimMargin()
    }
}
