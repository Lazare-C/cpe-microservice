document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('loginForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Empêche l'envoi normal du formulaire

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
                // Redirection après une réponse réussie
                window.location.href = "/cardGame/welcomePage.html";
            } else {
                // Gérer les erreurs de soumission
                const errorMessage = document.querySelector('.error-message');
                errorMessage.textContent = 'Erreur lors de la connexion';
                errorMessage.style.color = 'red';
            }
        }).catch(error => {
            console.error('Erreur:', error);
            const errorMessage = document.querySelector('.error-message');
            errorMessage.textContent = 'Erreur lors de la connexion';
            errorMessage.style.color = 'red';
        });
    });
});
