console.log(this.introduced);

function surligne(champ, erreur){
   if(erreur)
      champ.style.backgroundColor = "#fba";
   else
      champ.style.backgroundColor = "";
}

function verifDates() {
	var valid = true;
	var introduced = this.introduced.value;
	var discontinued = this.discontinued.value;
	
	if(introduced != ""){
		if(discontinued != ""){
			if(introduced > discontinued){
				valid = false;
				surligne(this.introduced, true);
			}
			else{
				surligne(this.introduced, false);
			}	
		}
	}
	
	if(discontinued != ""){
		if(introduced != ""){
			if(discontinued < introduced){
				
				valid = false;
				surligne(this.discontinued, true);
			}
			else{
				surligne(this.discontinued, false);
			}
		}
	}
	return valid;
}


function verifName(champ){
	console.log(champ.value);
	   if(champ.value == ""){
		  valid = false;
	      surligne(champ, true);
	      return false;
	   }
	   else{
	      surligne(champ, false);	
	      return true;
	   }
	}

function verifForm(f){
	var nameOk = verifName(f.computerName);
	var dateOk = verifDates();
   
	if(nameOk && dateOk){
		alert("Ajout de l'ordinateur dans la base");
		return true;
	}
	else{
		alert("Veuillez remplir les champs correctement");
		return false;
	}
}