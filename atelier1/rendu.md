*Après avoir dressé un bilan de réalisation de votre application, réaliser un tableau comparatif des
architectures logicielles possibles telles que MVC, SOA, MicroService…*

Après avoir réalisé les deux étapes de l'atelier, le site web statique puis le dynamique, nous avons pu constater que
chaque solution a ses avantages et ses inconvénients.
On note que les sites statiques charge plus rapidement et offre beaucoup plus d'intéractions avec l'utilisateur sans
recharger la page entière contrairement à une page dynamique qui ne contient pas de JavaScript.
Cependant, le site dynamique offre un développement plus rapide, car plus simple, ainsi qu'un meilleur SEO car le
contenue de la page est généré côté serveur avant l'envoi.

| Architecture | Description                                                                                                    | Avantages                                                                                                                                              | Inconvénients                                                            |
|--------------|----------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------|
| MVC          | application en trois composants interconnectés : Modèle, Vue, Contrôleur                                       | - Facile à comprendre et à mettre en place <br> - Un monolithe avec une couche réseau limitée                                                          | - Difficile à maintenir sur le long terme <br> - Difficile à tester <br> |
| SOA          | architecture qui se concentre sur des services au sein d'une application lieu d'une conception monolithique. . | - Facilite la réutilisation des services <br> - Facilite l'intégration de nouveaux services car une seule application et donc une seule base de donnée | - Complexité de mise en place <br> - Difficulté à maintenir              |
| MicroService | architecture de plusieurs micros applications autonomes                                                        | - Facilite la maintenance <br> - limite les failles                                                                                                    | - Complexité de mise en place                                            |

Mais on ne peut pas opposer ces architectures, car elles ne répondent pas aux mêmes besoins.
En effet, elles ont chacune des avantages et des inconvénients qui les rendent plus ou moins adaptées à un contexte
donné. Par exemple, le MVC est plus adapté pour des applications simples tandis que le MicroService est plus adapté pour
des applications complexes. Il est donc important de bien comprendre les besoins de l'application avant de choisir une
architecture.
