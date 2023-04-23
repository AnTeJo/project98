const form = document.querySelector('form');
const notification = document.querySelector('.popup h6');

document.querySelector('.card-number-input').oninput = () => {
  document.querySelector('.card-number-box').innerText = document.querySelector('.card-number-input').value;
}

document.querySelector('.card-holder-input').oninput = () => {
  document.querySelector('.card-holder-name').innerText = document.querySelector('.card-holder-input').value;
}

document.querySelector('.month-input').oninput = () => {
  document.querySelector('.exp-month').innerText = document.querySelector('.month-input').value;
}

document.querySelector('.year-input').oninput = () => {
  document.querySelector('.exp-year').innerText = document.querySelector('.year-input').value;
}

document.querySelector('.cvv-input').onmouseenter = () => {
  document.querySelector('.front').style.transform = 'perspective(1000px) rotateY(-180deg)';
  document.querySelector('.back').style.transform = 'perspective(1000px) rotateY(0deg)';
}

document.querySelector('.cvv-input').onmouseleave = () => {
  document.querySelector('.front').style.transform = 'perspective(1000px) rotateY(0deg)';
  document.querySelector('.back').style.transform = 'perspective(1000px) rotateY(180deg)';
}

document.querySelector('.cvv-input').oninput = () => {
  document.querySelector('.cvv-box').innerText = document.querySelector('.cvv-input').value;
}

form.addEventListener('submit', function(event) {
  event.preventDefault();

  const formData = new FormData(form);

  const data = {};
  for (const [key, value] of formData.entries()) {
    data[key] = value;
  }

  console.log('Sending data:', data);

  fetch('/api/v1/payments', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  .then(response => {
    console.log('Response:', response);
    return response.json();
  })
  .then(data => {
    console.log('Data:', data);
    notification.textContent = 'Код подтверждения отправлен успешно';
    notification.classList.add('success');
    form.reset();
  })
  .catch(error => {
    console.error('Error:', error);
    notification.textContent = 'Ошибка отправки кода подтверждения. Попробуйте еще раз позднее';
    notification.classList.add('error');
  });
});

$('.open-popup').click(function(e) {
  e.preventDefault();
  $('.popup-bg').fadeIn(800);
  $('html').addClass('no-scroll');
});

$('.close-popup').click(function() {
  $('.popup-bg').fadeOut(800);
  $('html').removeClass('no-scroll');
});
