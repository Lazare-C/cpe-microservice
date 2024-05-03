let cardList = [];
getAllCards();
function getAllCards(){
    const GET_ALL_CARDS_URL="http://tp.cpe.fr:8083/cards"; 
    let context =   {
                        method: 'GET'
                    };
        
    fetch(GET_ALL_CARDS_URL,context)
        .then(response => response.json())
            .then(response => callback(response))
            .catch(error => err_callback(error));

}

function callback(response){
    response.forEach(card => {
        cardList.push(card);
    });
    console.log(response);
    console.log(cardList);

    // Reference to the template element
    let template = document.querySelector("#selectedCard");

    // Loop through each card in cardList
    for (const card of cardList) {
        // Clone the template content
        let clone = document.importNode(template.content, true);

        // Replace placeholders in the cloned template with card data
        newContent= clone.firstElementChild.innerHTML
            .replace(/{{date}}/g, card.name)
            .replace(/{{family_src}}/g, card.smallImgUrl)
            .replace(/{{image_src}}/g, card.imgUrl)
            .replace(/{{family_name}}/g, card.family)
            .replace(/{{like}}/g, "4")
            .replace(/{{comment}}/g, "5")
            .replace(/{{button}}/g, "Voir plus");

        clone.firstElementChild.innerHTML= newContent;

        // Append the cloned card to the grid container
        let cardContainer = document.querySelector("#gridContainer");
        cardContainer.appendChild(clone);
    }
}

function err_callback(error){
    console.log(error);
}
