document.addEventListener('DOMContentLoaded', function() {

    console.log('Auth script loaded'); // Debug 1
    
    const user = JSON.parse(localStorage.getItem('user'));
    console.log('User from localStorage:', user); // Debug 2

    const signinContainer = document.getElementById('signin-container');
    const loggedinContainer = document.getElementById('loggedin-container');
    const userNameSpan = document.getElementById('user-name');
    const logoutBtn = document.getElementById('logout-btn');

    console.log('Elements:', { // Debug 3
        signinContainer,
        loggedinContainer,
        userNameSpan,
        logoutBtn
    });

    // Only proceed if elements exist
    if (!signinContainer || !loggedinContainer || !userNameSpan || !logoutBtn) return;
    
    if (user && user.username) {
        // User is logged in
        signinContainer.style.display = 'none';
        loggedinContainer.style.display = 'flex';
        userNameSpan.textContent = user.username;
    } else {
        // User is not logged in
        signinContainer.style.display = 'block';
        loggedinContainer.style.display = 'none';
    }

    // Logout functionality
    logoutBtn.addEventListener('click', function() {
        localStorage.removeItem('jwt');
        localStorage.removeItem('user');
        window.location.href = 'index.html';
    });
});