package com.guincode


enum class CategorieAnimal {
    CARNIVORE, HERBIVORE, INSECTIVORE, AUTRE
}

data class Animal(
    var categorie: CategorieAnimal,
    var nom: String,
    var poids: Double,
    var age:Int){

    fun detailsAnimal(){
        println("Nom: $nom, Poids: $poids, Age: $age, Appartient à la categorie $categorie")
    }
}



//singleton pour contenire mes functions qui ne sont pas liées à un objet precis
object ZooOperation{



    fun showAllAnimaux(listAnimaux: MutableList <Animal>){
        println("------------------------------")
        println("Liste des animaux")
        // boucle for avec le couple indice et valeur
        for ((indice,valeur) in listAnimaux.withIndex())
            println("${indice+1}) Categorie:${valeur.categorie}, Nom: ${valeur.nom}, Poids: ${valeur.poids} kg, Age: ${valeur.age} ans ")
        print("[Total animaux]: ${listAnimaux.size}")
        // les scope fonction
        listAnimaux.map { it }.filter{ it.categorie==CategorieAnimal.CARNIVORE}.count().let { print(", [Nombre Carnivore]: $it") }
        listAnimaux.map { it }.filter{ it.categorie==CategorieAnimal.HERBIVORE}.count().let { print(", [Nombre Herbivore]: $it") }
        listAnimaux.map { it }.filter{ it.categorie==CategorieAnimal.INSECTIVORE}.count().let { println(", [Nombre Insectivore]: $it") }
        listAnimaux.map { it }.filter{ it.categorie==CategorieAnimal.AUTRE}.count().let { println(", [Nombre Autre]: $it") }
        listAnimaux.sortedWith(compareBy { it.poids }).last().let { println("[GRAND POIDS] --> l'animal qui a le plus grand poid est: le ${it.nom} avec un poids = ${it.poids} kg") }

    }

    fun showAnimalByCategorie(listAnimaux: MutableList <Animal>, categorie: CategorieAnimal){

        //boucle for avec uniquement la valeur
        var i=0
        for (valeur in listAnimaux)
        //les Conditions avec when
            when(categorie){
                valeur.categorie-> {
                    println("${i+1}) Categorie:${valeur.categorie}, nom: ${valeur.nom}, poids: ${valeur.poids} kg, age: ${valeur.age} ans ")
                    i++
                }
            }
        println("Nombre de $categorie: $i")
        println("------------------------------")
    }


   fun removeAnimal(element: String):String{
       for ((indice,valeur) in listeAnimaux.withIndex())
           if(valeur.nom==element){
               listeAnimaux.apply {
                   removeAt(indice)
               }
                return "L'animal: [${valeur.nom}] à la position $indice à été rétiré de la liste"
           }

       return " aucun animal de la liste ne porte le nom $element "
   }

    fun findAnima(param: String){
       val elem=   listeAnimaux.find { it.nom==param }
        if (elem != null) {
            elem.detailsAnimal()
        }
        else println("aucun element trouvé")
    }
}


//les fonctions lamda
val isMemeCategorie={x: Animal, y:Animal ->   if (x.categorie != y.categorie) "[TEST CATEGORIE] --> ${x.nom} est un  ${x.categorie} alors que ${y.nom} est un ${y.categorie} "
else "[TEST CATEGORIE] --> ${x.nom} et ${y.nom} sont tous des ${x.categorie}  " }
val calclulPoind={x: Animal, y:Animal -> "[CALCUL POIDS] -->  Poids[${x.nom}] + Poids[${y.nom}] => ${x.poids} + ${y.poids} = ${x.poids + y.poids}" }

// HyghtOrderFontion
inline fun myHyghtOrderFunction(x: Animal, y: Animal, operation: (Animal, Animal) -> String):  Unit=  println(operation(x,y))



var lion=Animal(CategorieAnimal.CARNIVORE,"lion",12.3,2)
var zebre=Animal(CategorieAnimal.HERBIVORE,"zebre",43.3,3)
var leopard=Animal(CategorieAnimal.CARNIVORE,"leopard",43.0, 5)
var singe=Animal(CategorieAnimal.HERBIVORE, "singe",3.0, 1)
var herisson= Animal(CategorieAnimal.INSECTIVORE,"herisson",2.3,2)
var antilope= Animal(CategorieAnimal.HERBIVORE,"antilope",25.0,4)
var antilope2= Animal(CategorieAnimal.HERBIVORE,"antilope",25.0,4)
var anteater= Animal(CategorieAnimal.INSECTIVORE,"anteater",1.0,1)
var listeAnimaux= arrayListOf(lion,zebre,leopard,singe,antilope,herisson,anteater,antilope2)


fun menu(){
    println("A :Pour afficher la liste des animaux")
    println("N :Pour ajouter un animal")
    println("D :Pour suprimer un animal")
    println("R :Pour rechercher un animal")
    println("H :Pour afficher les Herbivores")
    println("C :Pour afficher les Carnivores")
    println("I :Pour afficher les Insectivores")
    println("G :Pour afficher l'annimal avec le plus grand poids")
    println("Autre touche : Pour lancer le programme par defaut")
    print("choix: ")

    var entre = readLine().toString()

  if (entre=="") throw ChoixVideException()
    var choixCategorie: CategorieAnimal = CategorieAnimal.AUTRE

    when(entre[0].uppercaseChar()) {
        'A'-> {
            println("Liste des animaux:")
            ZooOperation.showAllAnimaux(listeAnimaux)
        }
        'D'->{
            println("entrer le nom de l'animal à supprimé")
            var nomAnimal= readLine()
            while (nomAnimal=="") {
                print("Entrer un nom: ")
                nomAnimal = readLine()
            }
            if (nomAnimal != null) {
               println(ZooOperation.removeAnimal(nomAnimal))
                ZooOperation.showAllAnimaux(listeAnimaux)
            }
        }
        'N'->{
            print("Nom: ")
            var nom = readLine()
            while (nom !is String) {
                print("Entrer un nom: ")
                nom = readLine()
            }
            print("Poids:")
            var poids = readLine()?.toDoubleOrNull()
            while ((poids !is Double) ) {
                print("Entrer un poids valide ex: 12.00 ")
                poids = readLine()?.toDoubleOrNull()
            }
            print("Age: ")
            var age = readLine()?.toIntOrNull()
            while (age !is Int) {
                print("age invalide, reessayer: ")
                age = readLine()?.toIntOrNull()
            }
            println("choix de categorie: ")
            println("1: CARNIVORE")
            println("2: HERBIVORE")
            println("3: INSECTIVORE")
            println("4: CATEGORIE AUTRE")
            var categorie = readLine()?.toIntOrNull()
          //  if (categorie==null) throw ChoixVideException()
                while (categorie !is Int) {
                    println("choix de categorie: ")
                    println("1: CARNIVORE")
                    println("2: HERBIVORE")
                    println("3: INSECTIVORE")
                    println("4: CATEGORIE AUTRE")

                    categorie = readLine()?.toIntOrNull()

                    when(categorie) {
                        1 -> choixCategorie=CategorieAnimal.CARNIVORE
                        2 -> choixCategorie=CategorieAnimal.HERBIVORE
                        3 -> choixCategorie=CategorieAnimal.INSECTIVORE
                        4 -> choixCategorie=CategorieAnimal.AUTRE
                        else -> {
                            throw ChoixVideException()
                        }

                    }



                }

            listeAnimaux.add(listeAnimaux.size, Animal(choixCategorie, nom,poids,age))
            println("un(e) $nom ajouté")
           try {
               menu()
           } catch (e : ChoixVideException){
               println(e)
           }



        }
        'R'->{
            println("Recherche de (nom animal): ")

            var nomA = readLine()
            while (nomA !is String) {
                println("Recherche de (nom animal): ")
                nomA = readLine()
            }

            ZooOperation.findAnima(nomA)
        }
        'H'-> {
            println("Liste des herbivores: ")
            ZooOperation.showAnimalByCategorie(listeAnimaux,CategorieAnimal.HERBIVORE)
        }
        'C'-> {
            println("Liste des carnivore: ")
            ZooOperation.showAnimalByCategorie(listeAnimaux,CategorieAnimal.CARNIVORE)
        }
        'I'-> {
            println("Liste des insectivore: ")
            ZooOperation.showAnimalByCategorie(listeAnimaux,CategorieAnimal.INSECTIVORE)
        }
        'G'-> {
            listeAnimaux.sortedWith(compareBy { it.poids }).last().let { println("[GRAND POIDS] --> l'animal qui a le plus grand poid est: le ${it.nom} avec un poids = ${it.poids} kg") }

        }
        else -> {
            println("Tous les animaux")
            ZooOperation.showAllAnimaux(listeAnimaux) //affiche tous les animaux avec le nombre de chaque categorie
            println("----------------------------")
            println("categorie CARNIVORES")
            ZooOperation.showAnimalByCategorie(listeAnimaux,CategorieAnimal.CARNIVORE) //Affiche les animaux de la categorie CARNIVORE
            println("categorie Herbivores")
            ZooOperation.showAnimalByCategorie(listeAnimaux,CategorieAnimal.HERBIVORE) //Affiche les animaux de la categorie HERBIVORE
            println("categorie insectivores")
            ZooOperation.showAnimalByCategorie(listeAnimaux,CategorieAnimal.INSECTIVORE) //Affiche les animaux de la categorie INSECTIVORE
            println("categorie Autre ")
            ZooOperation.showAnimalByCategorie(listeAnimaux,CategorieAnimal.AUTRE) //Affiche les animaux de la categorie AUTRE
            println("------------------------------")
            myHyghtOrderFunction(antilope,zebre,isMemeCategorie) //verifie si les deux animaux sont de la meme categorie
            println("------------------------------")
           myHyghtOrderFunction(antilope,zebre,calclulPoind) //calcul le poids total des deux animaux
        }
    }
}
