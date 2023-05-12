# Projet M1 TNSID Architecture distribuée

CHEVRY Aurélie, LORTHIOIR Théo, MARLIÈRE Johan

## Parties fonctionnelles de l'application :

- Serveur de Registres **8761**
- Load Balancing
- Gateway **8080**
- Base de données (H2, stockée dans le dossier ressources de chaque Micro-Service)
- Collection Postman (jointe au projet)
- Protection OAuth 2.0 (intégrée au Front)
- Système de file RabbitMQ (voir explications plus bas)
- Front-End (Angular, obligatoire car groupe de 3)
- Github https://github.com/AurelieCh/PCDO

## Thème de l'application :

**PCDO est un site de vente de composants informatiques à l'instar de topachat, materiel.net, etc.**

Le choix de ce thème permet d'utiliser plusieurs Micro-services (comptes, composants, commandes,...) qui gèreront chacun une partie de l'application.
Chaque Micro-Service peut ainsi avoir sa base de données respective
Les comptes pourront être gérés par OAuth 2.0
Un système de file RabbitMQ permet de gérer le suivi de commande, ou la gestion des comptes (voir plus bas)
Le Load-Balancing permettra de rendre l'application plus robuste.
Le front-end permettra de faire office de vrai site de vente de composants informatiques

### Configuration de l'application

#### **Serveur de Registre (port 8761)**

Configuration par défaut du serveur de registre.

#### **Gateway (port 8080)**

Configuration par défaut, avec ajout des routes, et autorisation d'accès depuis le port 4200

#### **Micro-Service Commandes (port 8081)**

Configuration de la base de données H2 dans le dossier ./src/main/resources/data/dbcommandes.mv.db

Le Micro-Service intègre la gestion des commandes, ainsi que la gestion des files RabbitMQ (en coordination avec le Micro-Service Mail) pour le suivi de commandes.

#### **Micro-Service Composants (port 8082)**

Configuration de la base de données H2 dans le dossier ./src/main/resources/data/dbcomposants.mv.db

Le Micro-Service intègre la gestion des composants.

Un composant intègre une liste de caractéristiques, qui définit le type de composant dont il s'agit.
Le Micro-Service intègre une recherche par filtres permettant de réduire le trafic réseau.

#### **Micro-Service Comptes (port 8083)**

Configuration de la base de données H2 dans le dossier ./src/main/resources/data/dbcomptes.mv.db

Le Micro-Service intègre la gestion des comptes, ainsi que la gestion des files RabbitMQ (en coordination avec le Micro-Service Mail) pour la gestion de comptes.

#### **Micro-Service Facturations (port 8084)**

Configuration de la base de données H2 dans le dossier ./src/main/resources/data/dbfacturations.mv.db

Le Micro-Service intègre la gestion des facturations en coordination avec le Micro-Service Commandes.

#### **Micro-Service Mail (port 8085)**

Le Micro-Service gère les mails (hébergés sur Gmail, adresse : pcdosarl@gmail.com).
Le port du serveur SMTP (envoi de mails) est soit 25, soit 443, soit 587 selon le type de connexion du Micro-Service à Internet. Le mot de passe d'application dans le code est celui de l'adresse mail pcdosarl@gmail.com.

#### **Micro-Service Autobuild (port 8086)**

Configuration de la base de données H2 dans le dossier ./src/main/resources/data/dbautobuild.mv.db

Le Micro-Service intègre la gestion des configurations pré-faites, mais aussi la génération aléatoire de configurations, en coordination avec le Micro-Service Composants.

### Lancement de l'application

**Les différentes parties du projet peuvent être démarrées indépendamment :**

Les Micro-services peuvent être démarrés seuls, mais dans ce cas, il faudra effectuer les requêtes sur leurs adresses IP respectives.

Le Serveur Registre permet aux micro-services et à la gateway de communiquer : les micro-services démarrés peuvent communiquer entre eux si les requêtes sont effectuées sur leurs adressses IP respectives

La Gateway permet d'avoir une adresse IP unique (localhost:8080 ici) pour centraliser les requêtes, et ainsi faire du load balancing : Si plusieurs instances d'un micro-service sont démarrées et connectées au serveur de registre, effectuer plusieurs fois la même requête sur localhost:8080 permet de répartir la charge sur les différentes instances d'un même micro-service.

Si une requête nécessite de communiquer avec un autre micro-service, et que celui-ci ou le Serveur registre (qui permet la communication entre les différents micro-services) n'est pas démarré, la requête échoue avec un code d'erreur (500 ou 503 selon le type de requête)

_Une requête au Micro-Service mail ne retourne pas d'erreur de requête au Micro-Service appellant si la requête échoue (erreur d'envoi de mail ou serveur non-démarré)_

### RabbitMQ

L'exécution de RabbitMQ nécessite que ce dernier soit exécuté sur la machine hôte des micro-services.

### Front

Le front-end peut être lancé dans le projet "Front". L'adresse hôte est alors localhost:4200, et l'utilisateur peut accéder à l'interface web du site pour y effectuer les différentes requêtes permises par le back-end.

### Utilisation du Load Balancing

Un micro-service peut être dupliqué pour être instancié plusieurs fois, permettant de répartir la charge serveur équitablement entre les différentes instances.

Dans cette archive, le Micro-Service composant a déjà été dupliqué, et lancer les 2 projets correspondants permettra de faire du load balancing.

_Note : Les bases de données étant stockées dans le dossier resource de chacun des projets, les bases de données ne sont pas partagées, et une modification de la BDD sur une instance ne modifiera pas la BDD sur les autres instances._
