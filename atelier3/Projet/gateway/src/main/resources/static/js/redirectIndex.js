document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('registerForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        // Convert form data to URL-encoded string
        const formData = new FormData(form);
        const urlEncodedData = new URLSearchParams();
        for (const pair of formData) {
            urlEncodedData.append(pair[0], pair[1]);
        }

        fetch(form.action, {
            method: form.method,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: urlEncodedData.toString(),
        }).then(response => {
            if (response.ok) {
                window.location.href = "/cardGame/login.html";
            } else {
                if(response.status == 400){
                    alert('Cette utilisateur existe déjà (ce message n\'est pas bien en termes de sécurité) 😊');
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