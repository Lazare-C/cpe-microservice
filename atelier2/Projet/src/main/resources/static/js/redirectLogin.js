document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('loginForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Empêche l'envoi normal du formulaire

        const formData = new FormData(form);
        fetch(form.action, {
            method: form.method,
            body: formData,
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
