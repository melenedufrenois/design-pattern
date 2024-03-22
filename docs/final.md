# L3 design pattern report

- **Firstname**: Dufrénois
- **Lastname**: Mélène

---
Au départ :

La clareté des lignes de codes m'a aidé dans la compréhension de celui-ci.
La séparation des classes facilite la compréhension des différentes fonctionnalités du programme.

La répartition des variables entre les différentes classes peut être complexe.
Il était important de vérifier que les classes ne contenaient que les données nécessaires à leur bon fonctionnement, évitant ainsi toute confusion.

La structure modulaire permet des modifications spécifiques à chaque classe sans impact sur le reste du code.

---
Mes classes :

- **App**: Correspond au Main.

- **Todo** : Gère les tâches et les représente par un nom, un état et une description à partir de la classe WebCommand.

- **CsvInsertCommand**: Gère la logique liée à la command "insert", uniquement si le fichier est un .csv.

- **JsonInsertCommand**: Gère la logique liée à la command "insert", uniquement si le fichier est un .json.

- **ListCommand**: Gère la logique liée à la command "list".

- **MigrateCommand** : Gère la logique liée à la commande "migrate".

- **WebCommand** : Gère la logique liée à la commande "web".

- **Command**: Fournit une base commune pour les classes CsvInsertCommand, JsonInsertCommand, ListCommand, MigrateCommand et WebCommand éliminant la redondance du code partagé.

- **OptionsParser**: Analyse et traite les options passées en ligne de commande.

- **OptionsDone**: Analyse la commande à effectuer et vérifie si celle-ci est effectuée ou non.

Ce qui permet de séparer les différents blocs et information et ainsi d'obtenir un code clair.

---
Diagramme de classes **Mermaid** :

    classDiagram

    GhostTests -- App : Test
    
    App -- OptionsParser : Uses
    App -- OptionsDone : Uses
    App -- ListCommand : Uses
    App -- InsertCommand : Uses
    App -- Command : Uses
    App : main()
    App : exec()

    Todo -- WebCommand : Uses
    Todo : getName()
    Todo : setName()
    Todo : isDone()
    Todo : setDone()
    Todo : getDescription()
    
    Command -- OptionsDone : Uses
    Command -- OptionsParser : Uses  
    Command : Command()
    Command : isCommand()

    JsonInsertCommand -- Command : Extends
    JsonInsertCommand -- OptionsParser : Uses
    JsonInsertCommand : exec()
    JsonInsertCommand : support()

    CsvInsertCommand -- Command : Extends
    CsvInsertCommand -- OptionsParser : Uses
    CsvInsertCommand : exec()
    CsvInsertCommand : support()

    ListCommand -- Command : Extends
    ListCommand -- OptionsParser : Uses
    ListCommand : exec()
    ListCommand : support()

    MigrateCommand -- Command : Extends
    MigrateCommand -- OptionsParser : Uses
    MigrateCommand : exec()
    MigrateCommand : support()
    MigrateCommand : migrateJsonToJson()
    MigrateCommand : migrateJsonToCsv()
    MigrateCommand : migrateCsvToCsv()
    MigrateCommand : migrateCsvToJson()

    WebCommand -- Command : Extends
    WebCommand -- OptionsParser : Uses
    WebCommand -- OptionsDone : Uses
    WebCommand : exec()
    WebCommand : support()
    WebCommand : neededArgs()
    WebCommand : sendGetRequest()
    WebCommand : parseResponseToTodo()
    WebCommand : execute()
    WebCommand : getFileContent()
    WebCommand : process()
    WebCommand : getOptionsParser()

---

**How can a newcomer add a new command ?**
- Créer une nouvelle classe qui implémente la nouvelle commande.
- Modifier la classe App pour une redirection vers cette nouvelle classe.
- Adapter le switch case de la classe App.

**How can a newcomer add a new file-based datasource ?**

**How can a newcomer add a non-file-based datasource ?**

**How can a newcomer add a new attribute to a Todo ?**
- Modifier la classe Todo pour inclure le nouvel attribut souhaité, ainsi que ses méthodes getter et setter correspondantes.
- Mettre à jour les méthodes existantes de la classe Todo, si nécessaire.

**How can a newcomer add a new interface to the project ?**
- Créer une nouvelle interface avec les méthodes nécessaires.
- Implémenter cette interface dans les classes appropriées du projet, en fournissant une implémentation pour chaque méthode définie dans l'interface.

---
**A noter :**
J'ai pu travailler avec Sacha Duvivier.