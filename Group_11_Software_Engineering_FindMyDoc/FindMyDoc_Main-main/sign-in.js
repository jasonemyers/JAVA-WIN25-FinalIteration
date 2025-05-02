document.addEventListener("DOMContentLoaded", function () {
    const signinBtn = document.querySelector("#signin-btn");

    signinBtn.onclick = async function (event) {
        event.preventDefault(); // Prevent form from reloading page

        console.log("Sign in pressed");

        const emailInput = document.querySelector("#Email-Address");
        const passwordInput = document.querySelector("#Password");

        const email = emailInput.value.trim();
        const password = passwordInput.value.trim();

        if (!email || !password) {
            alert("Please enter both email and password.");
            return;
        }

        try {
            const response = await fetch("https://findmydocmain-production.up.railway.app/api/auth/local", {
                headers: {
                    "Content-Type": "application/json"
                },
                method: "POST",
                body: JSON.stringify({ identifier: email, password: password })
            });

            const data = await response.json();

            if (!response.ok) {
                throw new Error(data.message || "Login failed. Please try again.");
            }

            console.log("Login successful:", data);

            // Store user token
            localStorage.setItem("jwt", data.jwt);
            localStorage.setItem("user", JSON.stringify(data.user));

            // Redirect user to dashboard or homepage
            window.location.href = "index.html"; // Change to your actual dashboard page

        } catch (error) {
            console.error("Error:", error);
            alert(error.message);
        }
    };
});