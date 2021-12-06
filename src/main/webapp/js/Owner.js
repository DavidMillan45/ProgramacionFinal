$(document).ready(function () {
    $("body").hide().fadeIn(3000);
});

$(document).ready(function () {
    $("#petedit-button").click(function () {
        $(this).next('#editpet').slideToggle();
        $(this).toggleClass('active');


    });

});

$(document).ready(function () {
    $("#owneredit-button").click(function () {
        $(this).next('#owneredit-div').slideToggle();
        $(this).toggleClass('active');


    });

});

$(document).ready(function () {
    $("#createpetcase-button").click(function () {
        $(this).next('#createpetcase-div').slideToggle();
        $(this).toggleClass('active');


    });

});

$(document).ready(function () {
    $("#createpet-button").click(function () {
        $(this).next('#createpet').slideToggle();
        $(this).toggleClass('active');


    });

});


var rowId = 0

var indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB || window.shimIndexedDB;
const dbName = "petDB";

var request = indexedDB.open(dbName, 2);

request.onerror = function (event) {
    console.log("Database error");
};
request.onupgradeneeded = function (event) {
    var db = event.target.result;
    var objectStore = db.createObjectStore("pets", {keyPath: "id"});
    objectStore.createIndex("petNameInput", "petNameInput", {unique: false});
};

var request = indexedDB.open(dbName, 2);
request.onsuccess = function (event) {
    var db = event.target.result;
    var tx = db.transaction("pets");
    var objectStore = tx.objectStore("pets");
    objectStore.getAll().onsuccess = function (event) {
        //console.log(event.target.result);
        rowId = event.target.result.length;
    };
};


function habilitarMicrochip() {

    let id = document.getElementById("mcedit-input").value;

    if (document.getElementById("body-table").rows[id - 1].cells[4].innerText == "") {
        document.getElementById("Microchipnew-inputtext").disabled = false;
    } else {
        document.getElementById("Microchipnew-inputtext").disabled = true;
    }
}

//document.getElementById("mcedit-input").addEventListener("change", habilitarMicrochip);


//microchip, name, species, race, size, sex create pet
document.getElementById("petsave-button").onclick = function () {
    let pet_id = document.getElementById("pet_id-input").value;
    let microchip = document.getElementById("mc-input").value;
    let namePet = document.getElementById("petname-input").value;
    let species = document.getElementById("petspecies-input").value;
    let race = document.getElementById("rc-input").value;
    let size = document.getElementById("petsize-input").value;
    let sex = document.getElementById("petsex-input").value;
    let owner_id = document.getElementById("ownerid-input").value;
    let json = {
        pet_id: pet_id,
        microchip: microchip,
        namePet: namePet,
        species: species,
        race: race,
        size: size,
        sex: sex,
        owner_id: owner_id,

    }
    console.log(json)
    //console.log(document.getElementById("type-input").value, username, password,email,person_id,name,address,neighborhood)
    fetch('http://localhost:8080/ProgramacionFinal-1.0-SNAPSHOT/api/pets', {
        method: 'POST',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}
//pet_id, name, species, race, size, sex update pet edit pet
document.getElementById("savechanges-button").onclick = function () {
    let UPpet_id = document.getElementById("mcedit-input").value;
    let UPname = document.getElementById("petnameedit-input").value;
    let UPrace = document.getElementById("rcedit-input").value;
    let UPsize = document.getElementById("petsizeedit-input").value;
    let UPsex = document.getElementById("petsexedit-input").value;
    let json = {
        pet_id: UPpet_id,
        pet_name: UPname,
        species: UPrace,
        race: UPsize,
        size: UPsex,

    }
    fetch('http://localhost:8080/ProgramacionFinal-1.0-SNAPSHOT/api/pets/' + document.getElementById("mcedit-input").value, {
        method: 'PUT',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}
//ownerPojo.getUsername(), ownerPojo.getName(), ownerPojo.getAdress(),ownerPojo.getEmail() ,ownerPojo.getNeighborhood()
document.getElementById("savechangesowner-button").onclick = function () {
    //ownerPojo.getUsername(), ownerPojo.getName(), ownerPojo.getAdress(),ownerPojo.getEmail() ,ownerPojo.getNeighborhood()
    let username = document.getElementById("usernameownerdit-input");
    let direccion = document.getElementById("addressowneredit-input");
    let barrio = document.getElementById("neighborhoodowneredit-input");
    let json = {
        username: username,
        direccion: direccion,
        barrio: barrio,
    }

    fetch('http://localhost:8080/Proyecto-Final-1.0-SNAPSHOT/api/pets/'+document.getElementById("pet_idedit-input"), {
        method: 'PUT',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}
//create petcase
//String case_id,String created_at, String type, String description, String pet_id
document.getElementById("petcasesave-button").onclick = function () {
    let case_id = document.getElementById("case_id-input").value;
    let created_at = document.getElementById("date-input").value;
    let type = document.getElementById("typecase-input").value;
    let description = document.getElementById("descriptioncase-input").value;
    let pet_id = document.getElementById("pet_idcase-input").value;
    let json = {
        case_id: case_id,
        created_at: created_at,
        type: type,
        description: description,
        pet_id: pet_id,
    }
    console.log(created_at)
    //console.log(document.getElementById("type-input").value, username, password,email,person_id,name,address,neighborhood)
    fetch('http://localhost:8080/ProgramacionFinal-1.0-SNAPSHOT/api/petscase', {
        method: 'POST',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}
document.getElementById("updatepet-button").onclick = function () {
    //pet_id, name, species, race, size, sex, picture
    let pet_id = document.getElementById("pet_idedit-input").value;
    let name = document.getElementById("petnameedit-input").value;
    let size = document.getElementById("petsizeedit-input").value;
    let species = document.getElementById("petspeciesedit-input").value;
    let race = document.getElementById("rcedit-input").value;
    let sex = document.getElementById("petsexedit-input").value;


    let json = {
        pet_id: pet_id,
        name: name,
        size: size,
        species: species,
        race: race,
        sex: sex
    }

    fetch('http://localhost:8080/Proyecto-Final-1.0-SNAPSHOT/api/pets/' + document.getElementById("pet_id"), {
        method: 'PUT',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}


document.getElementById("petcasesave-button").onclick = function () {
    let case_id = document.getElementById("case_id-input").value;
    let created_at = document.getElementById("date-input").value;
    let type = document.getElementById("typecase-input").value;
    let description = document.getElementById("descriptioncase-input").value;
    let pet_id = document.getElementById("pet_idcase-input").value;
    let json = {
        case_id: case_id,
        created_at: created_at,
        type: type,
        description: description,
        pet_id: pet_id,
    }
    console.log(created_at)
    //console.log(document.getElementById("type-input").value, username, password,email,person_id,name,address,neighborhood)
    fetch('http://localhost:8080/ProgramacionFinal-1.0-SNAPSHOT/api/pets', {
        method: 'POST',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}