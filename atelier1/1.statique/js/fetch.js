function pushCard() {
    const GET_ALL_CARDS_URL = "http://tp.cpe.fr:8083/card";
    
    // Get form input values
    let formData = {
        date: "2021-11-11", // Static date or use new Date().toISOString() for current date
        name: document.querySelector('input[name="name"]').value,
        family: document.querySelector('input[name="family"]').value,
        imgUrl: document.querySelector('input[name="imgUrl"]').value,
        smallImgUrl: document.querySelector('input[name="smallImgUrl"]').value
    };

    // Convert formData to JSON string
    let jsonData = JSON.stringify(formData);

    // Configure fetch request
    let context = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    };

    // Send POST request
    fetch(GET_ALL_CARDS_URL, context)
        .then(response => response.json())
        .then(response => callback(response))
        .catch(error => err_callback(error));
}

function callback(response) {
    console.log(response);
    // Update HTML element with response value
    document.getElementById("txtChuck").innerHTML = response.value;
}

function err_callback(error) {
    console.error(error);
}
