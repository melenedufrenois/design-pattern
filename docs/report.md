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

- **App**

- **InsertCommand**: Gère la logique liée à l'insertion de données. Contient les variables nécessaires à l'insertion, évitant ainsi toute confusion avec d'autres données et variables.

- **ListCommand**: Gère la logique liée à la liste de commandes. Comprend les variables spécifiques à la fonction de liste, assurant une isolation claire des données.

- **Command**: Fournit une base commune pour les classes InsertCommand et ListCommand, éliminant la redondance du code partagé. Peut contenir des éléments partagés entre les différentes commandes.

- **OptionsParser**: Analyse et traite les options passées en ligne de commande. Inclut les variables nécessaires pour interpréter les options, contribuant à la clarté du code.

- **FileTodoDataSource**

- **Todo**

- **TodoDataSource**

- **FileTodoDataSourceFactory**

- **OptionsDone**

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

---
**A noter :** 
J'ai pu travailler avec Sacha Duvivier.

---
Pour le 26/02 : 

Rajouter commande "migrate" qui prend une source json et une 
destination csv tel que : 

"-s toto.json --dest toto.csv"

à noter que la commande peut faire :
- json --> json
- json --> csv
- csv --> json
- csv --> csv