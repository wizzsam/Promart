function openPaymentModal() {
    document.getElementById("paymentModal").style.display = "block";
}

function closePaymentModal() {
    document.getElementById("paymentModal").style.display = "none";
}

function pagoExitoso() {
    closePaymentModal();
    document.getElementById("successModal").style.display = "block";
}

function closeSuccessModal() {
    document.getElementById("successModal").style.display = "none";
}

// Close the modal when clicking outside of it
window.onclick = function(event) {
    var paymentModal = document.getElementById("paymentModal");
    var successModal = document.getElementById("successModal");
    if (event.target == paymentModal) {
        paymentModal.style.display = "none";
    } else if (event.target == successModal) {
        successModal.style.display = "none";
    }
 }
   function changeQuantity(productId, delta) {
        var inputField = document.querySelector('input[id$="' + productId + '"]');
        if (inputField) {
            var currentQuantity = parseInt(inputField.value);
            if (!isNaN(currentQuantity)) {
                var newQuantity = currentQuantity + delta;
                if (newQuantity >= 1) {
                    inputField.value = newQuantity;
                    inputField.dispatchEvent(new Event('change'));
                }
            }
        }
    }
     function openPaymentModal() {
        document.getElementById('paymentModal').style.display = 'block';
    }

    function closePaymentModal() {
        document.getElementById('paymentModal').style.display = 'none';
    }

    function pagoExitoso() {
        document.getElementById('paymentModal').style.display = 'none';
        document.getElementById('successModal').style.display = 'block';
        setTimeout(() => {
            document.getElementById('successModal').style.display = 'none';
            window.location.reload(); // Recarga la página para reflejar los cambios en el carrito
        }, 3000);
    }

    function closeSuccessModal() {
        document.getElementById('successModal').style.display = 'none';
        window.location.reload(); // Recarga la página para reflejar los cambios en el carrito
    }function openPaymentModal() {
    document.getElementById("paymentModal").style.display = "block";
}

function closePaymentModal() {
    document.getElementById("paymentModal").style.display = "none";
}

function pagoExitoso() {
    closePaymentModal();
    document.getElementById("successModal").style.display = "block";
}

function closeSuccessModal() {
    document.getElementById("successModal").style.display = "none";
}

// Close the modal when clicking outside of it
window.onclick = function(event) {
    var paymentModal = document.getElementById("paymentModal");
    var successModal = document.getElementById("successModal");
    if (event.target == paymentModal) {
        paymentModal.style.display = "none";
    } else if (event.target == successModal) {
        successModal.style.display = "none";
    }
 }
   function changeQuantity(productId, delta) {
        var inputField = document.querySelector('input[id$="' + productId + '"]');
        if (inputField) {
            var currentQuantity = parseInt(inputField.value);
            if (!isNaN(currentQuantity)) {
                var newQuantity = currentQuantity + delta;
                if (newQuantity >= 1) {
                    inputField.value = newQuantity;
                    inputField.dispatchEvent(new Event('change'));
                }
            }
        }
    }
     function openPaymentModal() {
        document.getElementById('paymentModal').style.display = 'block';
    }

    function closePaymentModal() {
        document.getElementById('paymentModal').style.display = 'none';
    }

    function pagoExitoso() {
        document.getElementById('paymentModal').style.display = 'none';
        document.getElementById('successModal').style.display = 'block';
        setTimeout(() => {
            document.getElementById('successModal').style.display = 'none';
            window.location.reload(); // Recarga la página para reflejar los cambios en el carrito
        }, 3000);
    }

    function closeSuccessModal() {
        document.getElementById('successModal').style.display = 'none';
        window.location.reload(); // Recarga la página para reflejar los cambios en el carrito
    }