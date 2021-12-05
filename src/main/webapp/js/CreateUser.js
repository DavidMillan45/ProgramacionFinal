document.getElementById("usersave-button").onclick = function () {
    let username = document.getElementById("username-input").value;
    let password = document.getElementById("password-input").value;
    let email = document.getElementById("email_id-input").value;
    let person_id = document.getElementById("Person_id-input").value;
    let name = document.getElementById("name-input").value;
    let address = document.getElementById("address-input").value;
    let neighborhood = document.getElementById("neighborhood-input").value;
    let json = {

        username: username,
        password: password,
        email: email,
        person_id: person_id,
        name : name,
        address: address,
        neighborhood: neighborhood,
    }
    console.log(json)
    //console.log(document.getElementById("type-input").value, username, password,email,person_id,name,address,neighborhood)
    fetch('http://localhost:8080/ProgramacionFinal-1.0-SNAPSHOT/api/' + document.getElementById("type-input").value+'s', {
        method: 'POST',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}
/*
// funciones para modificar y agregar de los usearios  y mascotas

//actualizar owner
document.getElementById("updateowner-button").onclick = function () {
    //ownerPojo.getUsername(), ownerPojo.getName(), ownerPojo.getAdress(),ownerPojo.getEmail() ,ownerPojo.getNeighborhood()
    let username = document.getElementById("username-input");
    let nombre = document.getElementById("nombreowner-input");
    let direccion = document.getElementById("address-input");
    let email = document.getElementById("email-input");
    let barrio = document.getElementById("neighborhood-input");
    let json = {
        username: username,
        nombre: nombre,
        direccion: direccion,
        email: email,
        barrio: barrio,
    }

    fetch('http://localhost:8080/Proyecto-Final-1.0-SNAPSHOT/api/pets/'+document.getElementById("usernameOwner"), {
        method: 'PUT',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}

// actualizar  vet
document.getElementById("updatevet-button").onclick = function () {
    let username = document.getElementById("username-input");
    let nombre = document.getElementById("nombreowner-input");
    let email = document.getElementById("email-input");
    let barrio = document.getElementById("neighborhood-input");
    let json = {
        username: username,
        nombre: nombre,
        email: email,
        barrio: barrio,
    }

    fetch('http://localhost:8080/Proyecto-Final-1.0-SNAPSHOT/api/vets/'+document.getElementById("usernameVet"), {
        method: 'PUT',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}


//actualizar mascota

document.getElementById("updatepet-button").onclick = function () {
    //pet_id, name, species, race, size, sex, picture
    let pet_id = document.getElementById("pet_id-input");
    let name = document.getElementById("namepet-input");
    let race = document.getElementById("racepet-input");
    let size = document.getElementById("sizepet-input");
    let sex = document.getElementById("sexpet-input");
    let picture = document.getElementById("picturepet-input");

    let json = {
        pet_id: pet_id,
        name: name,
        race: race,
        size: size,
        sex: sex,
        picture: picture,
    }

    fetch('http://localhost:8080/Proyecto-Final-1.0-SNAPSHOT/api/pets/'+document.getElementById("pet_id"), {
        method: 'PUT',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}
*/