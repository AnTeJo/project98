const form = document.getElementById('payment-form');

form.addEventListener('submit', function(event) {
  event.preventDefault();

  const cardNumber = form.querySelector('#cardNumber').value;
  const cardHolderName = form.querySelector('#cardHolderName').value;
  const expirationDate = form.querySelector('#expirationDate').value;
  const cardCvv = form.querySelector('#cardCvv').value;

  const paymentData = { cardNumber, cardHolderName, expirationDate, cardCvv };

  fetch('/payments', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(paymentData)
  })
  .then(response => response.json())
  .then(data => {
    alert('Payment created successfully');
    form.reset();
  })
  .catch(error => {
    console.error('Error:', error);
    alert('An error occurred. Please try again later.');
  });
});