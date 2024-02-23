# L3 design pattern report

- **Firstname**: Dufrénois
- **Lastname**: Mélène


> Add your thoughts on every TP bellow, everything is interresting but no need to right a book.
> 
> Keep it short simple and efficient:
> 
> - What you did and why
> - What helped you and why
> - What did you find difficult
> - What did not help you
> - What did you need to change
> - Anything relevant
> 
> Add a link to schemas describing your architecture (UML or not but add a legend)
> 
> Remember: it is ok to make mistakes, you will have time to spot them later.
> 
> Fill free to contact me if needed.

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

- **InsertCommand**: Gère la logique liée à la command "insert".

- **ListCommand**: Gère la logique liée à la command "list".

- **MigrateCommand** : Gère la logique liée à la commande "migrate".

- **Command**: Fournit une base commune pour les classes InsertCommand et ListCommand, éliminant la redondance du code partagé.

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
    
    Command -- OptionsDone : Uses
    Command -- OptionsParser : Uses  
    Command : Command()
    Command : isCommand()
    
    InsertCommand -- Command : Extends
    InsertCommand -- OptionsParser : Uses
    InsertCommand : exec()
    InsertCommand : support()

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

---
**A noter :** 
J'ai pu travailler avec Sacha Duvivier.