## Описание

Эта программа реализует CRUD (Создание, Чтение, Обновление, Удаление) операции для управления записями автомобилей в базе данных. Класс `CarRepositoryImpl` реализует интерфейс `CarRepository` и предоставляет полный набор функций для манипуляции данными автомобилей.

findById(Long carId): Возвращает автомобиль по его идентификатору (ID).
findAll(): Возвращает список всех автомобилей, доступных в базе данных.
insert(CarEntity car): Добавляет новый автомобиль в базу данных и возвращает идентификатор новой записи.
update(CarEntity car): Обновляет информацию о существующем автомобиле.
remove(Long carId): Удаляет автомобиль из базы данных по его ID.


## Тестирование

Для этого класса написаны unit-тесты, проверяющие каждую функциональность. Тесты используют библиотеки JUnit и Mockito для мокирования зависимостей и проверки корректности работы методов.
