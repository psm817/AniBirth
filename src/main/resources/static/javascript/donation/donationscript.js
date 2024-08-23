document.addEventListener('DOMContentLoaded', function() {
    // Donation Modal Logic
    var donateModal = document.getElementById("donateModal");
    var btnDonate = document.getElementById("btnDonate");
    var closeDonateModal = document.getElementsByClassName("close")[0];

    if (btnDonate) {
        btnDonate.addEventListener('click', function() {
            donateModal.style.display = "block";
        });
    }

   var closeDonateModal = document.getElementsByClassName("close")[0];
   if (closeDonateModal) {
       closeDonateModal.addEventListener('click', function() {
           donateModal.style.display = "none";
       });
   }

    window.addEventListener('click', function(event) {
        if (event.target == donateModal) {
            donateModal.style.display = "none";
        }
    });

    const submitDonationBtn = document.getElementById('submitDonation');
    if (submitDonationBtn) {
        submitDonationBtn.addEventListener('click', function() {
            const recipient = document.getElementById('recipient').value;
            const amount = document.getElementById('amount').value;
            if (recipient && amount) {
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = '/donation/submit';

                const recipientInput = document.createElement('input');
                recipientInput.type = 'hidden';
                recipientInput.name = 'recipientId';
                recipientInput.value = recipient;
                form.appendChild(recipientInput);

                const amountInput = document.createElement('input');
                amountInput.type = 'hidden';
                amountInput.name = 'amount';
                amountInput.value = amount;
                form.appendChild(amountInput);

                document.body.appendChild(form);
                form.submit();
            } else {
                alert('모든 필드를 입력하세요.');
            }
        });
    }


    document.getElementById('donationForm').addEventListener('submit', function(event) {
        var amount = document.getElementById('amount').value;
        if (!amount) {
            event.preventDefault(); // Prevent form submission
            document.getElementById('amountError').style.display = 'block';
        }
    });

    // Alert Modal Logic
    var alertModal = document.getElementById("messageModal");
    var closeAlertModal = document.querySelector("#messageModal .close");
    var closeAlertBtn = document.querySelector("#messageModal .btn-close");

    if (alertModal) {
        alertModal.style.display = "block";

        closeAlertModal.onclick = function() {
            alertModal.style.display = "none";
        };

        closeAlertBtn.onclick = function() {
            alertModal.style.display = "none";
        };

        window.onclick = function(event) {
            if (event.target == alertModal) {
                alertModal.style.display = "none";
            }
        };

        setTimeout(function() {
            alertModal.style.display = "none";
        }, 5000); // Automatically hide after 5 seconds
    }
});
