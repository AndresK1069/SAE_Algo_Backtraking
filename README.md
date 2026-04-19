# SAE 2.02 : Exploration algorithmique d'un problème

## Objectifs

- S'autoformer pour comprendre les algorithmes de backtracking (retour sur trace)
- Savoir implémenter un tel algorithme sur un problème nouveau
- Présenter un tel algorithme lors d'une soutenance

## Problèmes de backtracking

Lire la page suivante pour comprendre ce qu'est un algorithme de backtracking et comment cela fonctionne : https://fr.wikipedia.org/wiki/Retour_sur_trace

Liste de problèmes pouvant être résolus par un tel algorithme :

0. Enumération des chemins dans un graphe : étant donné un graphe et deux sommets s et t, énumérer tous les chemins simples reliant s à t (un chemin simple ne passe pas deux fois par le même sommet)
1. Problème des n reines
2. Sudoku nxn (ou autres jeux du même type)
3. Problème du sac-à-dos 0-1
4. Couverture d'un carré avec des formes données
5. Coloration de graphes
6. Problème de la somme cible (subset sum)
7. Chemin hamiltonien dans un graphe
8. Réalisation d'emplois du temps sous contraintes

## Questions générales de compréhension

- Quelle est la différence entre backtracking et recherche exhaustive ?
- Dans quels types de problèmes cette approche est-elle pertinente ?
- Pourquoi le backtracking est-il souvent implémenté avec une récursion ?
- Peut-on l'implémenter sans récursion et comment ?
- Qu'est-ce qu'un état partiel ?
- Qu'est-ce qu'une solution complète ?
- Qu'est-ce qu'un arbre de recherche ?
- Quel est le lien entre backtracking et parcours en profondeur (DFS) ?
- Pourquoi les algorithmes de backtracking sont-ils souvent exponentiels ?
- Qu'est-ce que l'élagage (pruning) et pourquoi améliore-t-il les performances ?

## Objectifs

### La soutenance

La soutenance durera 10 minutes par groupe :

- 5 minutes : présentation d'une implémentation en Java d'un algorithme de backtracking sur un problème au choix (hormis le problème 0)
- 5 minutes : questions sur le travail

La soutenance sera appuyée par un fichier PDF de transparents. Les points à expliquer :

- Ce qu'est le backtracking
- Le problème choisi
- Comment ont été créées ou récupérées les instances du problème
- Comment fonctionne l'algorithme particulier et le programme en général (fonctionnalités)
- Une comparaison des temps de calcul pour des instances de taille croissante (graphique recommandé)

Le langage choisi pour le programme est obligatoirement Java. Les transparents sont obligatoirement au format PDF.

### L'écrit

Un devoir sur table individuel aura lieu suite aux soutenances, portant sur des exercices et questions liées au backtracking sur des problèmes nouveaux.

## Conseils

- Commencer par résoudre le problème 0 (énumération des chemins d'un graphe) : c'est le problème fondamental qui permet de comprendre les autres
- Essayer de comprendre le principe de résolution de plusieurs problèmes de la liste, même ceux non choisis
- Il n'est pas interdit de travailler à plusieurs binômes pour la compréhension, à partir du moment où le travail final est fait par son propre binôme
