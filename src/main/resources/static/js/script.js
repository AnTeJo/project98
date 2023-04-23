   const form = document.querySelector('form');

    form.addEventListener('submit', function(event) {
      event.preventDefault();

      const data = {
        id: form.id.value,
        cardNumber: form.cardNumber.value,
        cardName: form.cardName.value,
        cardHolderName: form.cardHolderName.value,
        expirationDate: form.expirationDate.value,
        cardCvv: form.cardCvv.value,
        cardAmount: form.cardAmount.value
      };

      console.log('Sending data:', data);

      fetch('/api/v1/payments', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      })
      .then(response => response.json())
      .then(data => {
        console.log('Payment created:', data);
        alert('Payment created successfully');
        form.reset();
      })
      .catch(error => {
        console.error('Error:', error);
        alert('An error occurred. Please try again later.');
      });
    });