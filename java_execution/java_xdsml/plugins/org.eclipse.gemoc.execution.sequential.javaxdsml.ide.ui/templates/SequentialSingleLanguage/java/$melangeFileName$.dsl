DSL $packageName$.$languageName$ {
	
	abstract-syntax {
		ecore = "platform:/resource/$ecoreFilePath$"
	}
	
	semantic {
$listOfAspects$
	}
}