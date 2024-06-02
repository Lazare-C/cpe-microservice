document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('registerForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(form);
        fetch(form.action, {
            method: form.method,
            body: formData,
        }).then(response => {
            if (response.ok) {
                window.location.href = "/cardGame/login.html";
            } else {
                if(response.status == 400){
                    alert('Cette utilisateur existe deja (ce message est pas bien en termes de securiter) 😊');
                }else{
                    alert('Erreur lors de l\'inscription');
                }
            }
        }).catch(error => {
            console.error('Erreur:', error);
            alert('Erreur lors de l\'inscription');
        });
    });
});