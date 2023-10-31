document.addEventListener('DOMContentLoaded', function() {
    const contentDiv = document.getElementById('content');
    const loginLink = document.querySelector('a[href="#login"]');
    const registerLink = document.querySelector('a[href="#register"]');
  
    loginLink.addEventListener('click', function(event) {
      event.preventDefault();
      loadContent('login.html');
    });
  
    registerLink.addEventListener('click', function(event) {
      event.preventDefault();
      loadContent('register.html');
    });
  
    function loadContent(url) {
      fetch(url)
        .then(response => response.text())
        .then(data => {
          contentDiv.innerHTML = data;
        })
        .catch(error => console.error('Error loading content:', error));
    }
  });
  