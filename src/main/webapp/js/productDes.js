function openModal() {
    document.getElementById("myModal").style.display = "flex";
}

function closeModal() {
    document.getElementById("myModal").style.display = "none";
}

function processPayment() {
    // Mostrar confetti
    confetti({
        particleCount: 100,
        spread: 70,
        origin: { y: 0.6 }
    });

    // Redirigir a product.xhtml después de un breve retraso
    setTimeout(function() {
        closeModal();
        window.location.href = 'product.xhtml'; // Redirigir a product.xhtml
    }, 3000);
}

function showAvailability() {
    document.getElementById("availabilityModal").style.display = "flex";
}

function closeAvailabilityModal() {
    document.getElementById("availabilityModal").style.display = "none";
}

window.onclick = function(event) {
    if (event.target == document.getElementById("myModal")) {
        closeModal();
    }
    if (event.target == document.getElementById("availabilityModal")) {
        closeAvailabilityModal();
    }
}