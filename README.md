<h1>Додаткові матеріали</h1>
<h2>Відеоогляд системи:https://youtu.be/V24qyuxyAW0</h2>

<h2>Розробка нових модулів</h2>
Оскільки одною із вимог було легке розробка нових модулів, то був розроблений наступний 
механізм. Для розробки потрібно завантажити код із https://github.com/Zhukowych/RinitGUI, і 
відкрити його у своєму середовищі розробки (я використовую Eclipse). Потрібно знайти пакет
dev. Загальна концепція швидкої розробки така: ми пишемо мовий модуль в клонованому проекті, 
тим самим не тратячи часу на постійну компіляцію нового модулю, а потім, коли розробка
завершена, то цей новий модуль компілюється, і ми ввантажуємо в основну програму.

<h3>Розробка модуля-драйвера файлу</h3>

Як було показано вище функціонал дозволяє протестувати просту сторінку. Прийшов час розширювати
можливості. Розумним буде додати можливість тестування інтерфейсів API. А саме буде використовуватися
технологія GraphQl через свою наглядність.

Драйвер складається із двох частин: клієнтської частити, тобто інформації про те, як відображати
файли з певним розширенням, і частини, де зберігається інформація про те, як саме зберігати
данні на сервері (формат XML). Програмно ці дві чатини є наслідники класу AbstractCliDriver, 
що уособлює клієнтську частину, і наслідників AbstractDriver, що зберігає програму збирання 
і читання із сервера.

Для цього у пакеті dev.drivers потрібно створити новий пакет, в якому будуть зберігатися файли
нового модуля, назвемо його graphql. Тут також потрібно створити пакет driver для наслідника 
AbstractDriver, оскільки до нього додадуться ще декілька допоміжних. 

Тепер у dev.drivers.graphql потрібно створити файл GraphqlCliDriver.java і унаслідувати цей
клад від AbstractCliDriver. Потрібно буде ще реалізувати декілька методів:

| Назва методу | призначення |
| ---- | --- |
|getName | розширення файлу, файли якого зможе читати данний драйвер|
|getView | графічне представлення файлу, для його читання та редагування|
|isPopup | якщо файл, то чи відкривати під це окреме вікно|
|getPopUpSize | розмір вікна, якщо таке відкривається|
|isDirable | чи файл може бути каталогом|
|getDriver | посилення на клас наслідника AbstractDriver|

Також потрібно додати два конструктори: по замовчуваню, та, який приймає два параметри:
FileDTO readingFile, ModelFacade modelFacade. readingFile - це клас запису файлу в базі
данних, який користувач відкрив для читання/запису. modelFacade - це клас який забезпечує 
управління певними частинами клієнта(документація до нього буде додана нижче). Клас 
FileDTO зберігає в собі такі поля, як id, name, extention, path, position, content, гетери
і сетери до цих полів. Крім того потрібно створити клас-відображення. Назвемо його 
GraphqlCliDriverView.java, його потрібно наслідувати від класу AbstractCliDriverView. Після 
реалізації всіх методів файл GraphqlCliDriver.java буде виглядати так:

```java
package com.rinit.gui.dev.drivers.graphql;

\\ імпотрти приховані

public class GraphqlCliDriver extends AbstractCliDriver {

    private static final long serialVersionUID = 1780372510614788486L;

    public static final String NAME = "graphql";
    
    private GraphqlCliDriverLogic logic;
    private AbstractCliDriverView view;
    
    public GraphqlCliDriver() {}
    
    public GraphqlCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
        super(readingFile, modelFacade);
        this.logic = new GraphqlCliDriverLogic(readingFile, modelFacade);
        this.view = new GraphqlCliDriverView(logic);
    }
    
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public AbstractCliDriverView getView() {
        return this.view;
    }

    @Override
    public boolean isPopup() {
        return true;
    }

    @Override
    public Dimension getPopUpSize() {
        return null;
    }
    
    @Override
    public boolean isDirable() {
        return true;
    }

    @Override
    public Class<? extends AbstractDriver> getDriver() {
        return GraphqlDriver.class;
    }
    
}
```

Як видно додано ще декілька класів: GraphqlCliDriverView, що є відображенням, і 
GraphqlCliDriverLogic. Останній виконує роль логіки відображення, тому що для успішної розробки 
такого роду застосунків важливо, щоб частина програми, яка безпосередньо займається побудовою 
графійного інтерфейсу була відокремлена від того, що саме потрібно робити, коли натискаєш кнопку 
"зберегти".

Не буде продставлено повністю вміст файлів GraphqlCliDriverView та GraphqlCLiDriverLogic,
через те, що їх зміст не є важливим для розкриття теми. 

Далі потрібно створити клас, що буде читати/записувати данні у базу данних. Назвемо його 
GraphqlDriver.java, наслідуємо його від AbstractDriver. Потрібно реалізувати два методи:
buildFromDTO та buildContent. Перший займається тим, що бере данні із текстової стрічки, і
заносить їх у java-об'єкт. buildContent має  взяти поточні данні об'єкта, і на їх основі 
створити текстову-стрічку, яку потім запишуть у базу данних. 

Тепер потрібно визначитись із спеціальними данними, які повинен зберігати graphql запит. Для 
наочності припустимо, що такий запит має в собі тільки рядок graphql-запиту, який містить 
інформацію про данні, що сервер має повернути. Тому в клас GraphqlDriver потрібно додати 
поле, що буде зберігати цей запит, гетери, сетери до нього. 

Також потрібно реалізувати методи buildFromDTO та buildContent. Після виконання всього вище
сказаного клас набуде наступного вигляду. Для використання данніго класу у цілях тестування
потрібно заімплементувати його від інтерфейсу DebuggerDriver.

```java
package com.rinit.gui.dev.drivers.graphql.driver;

// імпорти приховані

public class GraphqlDriver extends AbstractDriver implements DebuggerDriver {
    
    private String query;
    
    public String getQuery() {
        return query;
    }
    
    public void setQuery(String query) {
        this.query = query;
    }
    
    @Override                           
    protected void buildFromDTO() {     
        XMLReader reader = new XMLReader(this.getContent());
        this.setQuery(reader.getTagValueByName("query", "graphql"));
    }
    
    @Override
    public String buildContent() {
        XMLBuilder builder = new XMLBuilder();
        return builder.addTag("graphql", builder.addTag("query", this.getQuery()));
    }
    
    @Override
    public void run(RunContext context) {
        ReportContext reportContext = context.getContext(ReportContext.class);
        ReportItem report = ReportItem.createDefaultReport(this);
        report.shortReport = ""; // корокий звіт
        report.fullReport = ""; // повнй звіт
        reportContext.addReport(report);        
    }

    @Override
    public void outRun(RunContext context) {
        // TODO Auto-generated method stub
        
    }

}
```

Стрічка, що є вмістом файлу має xml формат, тому для запису/читання можна використовувати
будь-яку бібліотеку. Після того, як данні із графічного інтерфейсу зчитані можна приступити до 
збереження файлу у базу данних

```java
  private void save() {
      IFileService fileService = modelFacade.getRinitClientModel() \
          .getClient().getFileService();
      /* отримати доступ до файлового сервісу */
      GraphqlDriver file = new GraphqlDriver();
      file.fromDTO(readingFile); 
      /* прочитати ім'я, розширення, шлях із файлу, що 
      відкрили для читання */
      file.setQuery(/* graphql-запит прочитаний із графічного інтерфейсу*/);
      fileService.saveFile(file);
      /* зберегти файл у файловому сервісі */
  }
```

Після відтворення цих дій ви можете скомпілювати класи у jar-файл, і завантажити на сервер, 
задопомогою команди F1
