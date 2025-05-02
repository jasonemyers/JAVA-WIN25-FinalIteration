FindMyDoc Website
FindMyDoc is a simple full-stack web application that helps users discover and review doctors in their area. 
It uses a React frontend and a Strapi CMS backend.

Features:
Browse doctors by specialty, location or name
View detailed profiles (photo, education, experience, contact info, description)
Read and leave star-rated reviews with custom tags
Register and log in to post your own reviews
Admin dashboard (Strapi) for managing doctors, users and reviews

Tech Stack
Frontend: React, React Router, plain CSS
Backend: Strapi v4 (Node.js) with PostgreSQL
Image hosting: Cloudinary
Authentication: JWT via Strapi Users & Permissions

Hosted On
Website (React app): https://austinhills.github.io/FindMyDoc/
Strapi Admin panel: https://findmydocmain-production.up.railway.app/admin/auth/login

Logins for strapi:
Username: Hp8822@wayne.edu
Password: Rafid1974

Deployment (Railway)
Both frontend and backend are deployed on Railway. On each push to main, Railway will build and deploy:

Strapi backend → available at your backend URL

React frontend → available at your production URL
Use the Railway dashboard to start, monitor and scale both services.

Local Development
If you want to run everything locally:

Run Strapi:
    cd the FindMyDoc file
    npm install
    npm run develop
    Strapi will be running at http://localhost:1337

Run React:
    cd the frontend file
    npm install
    npm start
    The React app will be running at http://localhost:3000

Enjoy finding and reviewing doctors with FindMyDoc!