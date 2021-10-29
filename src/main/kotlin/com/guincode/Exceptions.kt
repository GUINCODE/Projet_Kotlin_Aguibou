package com.guincode


class ChoixVideException():Exception("Choix vide ou invalide")
class BadDataFormatException(val data:Any):Exception("$data est un format de donnée incorrect pour ce champ")