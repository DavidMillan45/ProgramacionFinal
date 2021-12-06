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
    $("body").hide().fadeIn(3000);
});

$(document).ready(function () {
    $("#petedit-button").click(function () {
        $(this).next('#editpet').slideToggle();
        $(this).toggleClass('active');


    });

});
//String created_at, String type, String description, String pet_id
document.getElementById("petsave-button").onclick = function () {
    let created_at = document.getElementById("date-input").value;
    let type = document.getElementById("petsize-input").value;
    let description = document.getElementById("description-input").value;
    let pet_id = document.getElementById("Pet_id-input").value;
    let visit_id = document.getElementById("vetvisitid-input").value;
    let username = document.getElementById("Username-input").value;

    let json = {

        created_at: created_at,
        type: type,
        description: description,
        pet_id: pet_id,
        visit_id: visit_id,
    }
    console.log(json)
    //console.log(document.getElementById("type-input").value, username, password,email,person_id,name,address,neighborhood)
    fetch('http://localhost:8080/ProgramacionFinal-1.0-SNAPSHOT/api/visits' , {
        method: 'POST',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}

document.getElementById("savechanges-button").onclick = function () {
    let username = document.getElementById("Username-input");
    let address = document.getElementById("addressedit-input");
    let neighborhood = document.getElementById("neighborhoodedit-input");
    let json = {
        username: username,
        address: address,
        neighborhood: neighborhood,
    }

    fetch('http://localhost:8080/Proyecto-Final-1.0-SNAPSHOT/api/vets/' + document.getElementById("Username-input"), {
        method: 'PUT',
        body: JSON.stringify(json),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
}
