let pathname = window.location.pathname.split("/");

pathname[pathname.length - 1] = "multiPartServlet";
pathname = pathname.join("/");
const servletUrl = window.location.host + pathname;
// Making a fetch call to the servlet
fetch("http://" + servletUrl)
    .then(response => response.json())
    .then(data => {
        for (var i = 0; i < data.length; i++) {
            let trbody = document.createElement("tr");
            let td1 = document.createElement("td");
            let td2 = document.createElement("td");
            let td3 = document.createElement("td");

            var img = new Image();
            td1.innerHTML = data[i].date;
            td2.innerHTML = data[i].name;
            img.src = "/Taller3-Programacion2-1.0-SNAPSHOT//uploads/" + data[i].namePhoto;

            img.width = "200";
            img.height = "200";
            td3.appendChild(img);
            trbody.appendChild(td1);
            trbody.appendChild(td2);
            trbody.appendChild(td3);
            document.getElementById("body-table").appendChild(trbody);


        }
    });






