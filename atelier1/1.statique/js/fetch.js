function getAllCards(){
    const GET_ALL_CARDS_URL="http://tp.cpe.fr:8083/card"; 
    let context =   {
                        method: 'GET'
                    };
        
    fetch(GET_ALL_CARDS_URL,context)
        .then(response => response.json())
            .then(response => callback(response))
            .catch(error => err_callback(error));

}

function getCardById(id){

    const GET_CHUCK_URL="http://tp.cpe.fr:8083/card/" + id; 
    let context =   {
                        method: 'GET'
                    };
        
    fetch(GET_CHUCK_URL,context)
        .then(response => response.json())
            .then(response => callback(response))
            .catch(error => err_callback(error));
}

function callback(response){
    console.log(response);
    document.getElementById("txtChuck").innerHTML = response.value;
}

function err_callback(error){
    console.log(error);
}