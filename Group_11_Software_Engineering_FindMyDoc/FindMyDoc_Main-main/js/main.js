document.addEventListener('DOMContentLoaded', function() {
    // Show popup on initial load
    fetchPopupContent()
        .then(popupData => {
            createTooltipPopup(popupData);
            setupHelpButton(popupData);
        })
        .catch(error => {
            console.error('Error loading popup:', error);
        });

    // Search form handler
    document.getElementById('searchForm').addEventListener('submit', function(e) {
        e.preventDefault();
        const name = encodeURIComponent(document.getElementById('searchName').value);
        const specialty = encodeURIComponent(document.getElementById('searchSpecialty').value);
        const location = encodeURIComponent(document.getElementById('searchLocation').value);
        window.location.href = `/doctors?name=${name}&specialty=${specialty}&location=${location}`;
    });
});

async function fetchPopupContent() {
    try {
        const response = await fetch('https://findmydocprojectpythonserver-production.up.railway.app/popup-content');
        if (!response.ok) throw new Error('Network response failed');
        return await response.json();
    } catch (error) {
        throw error;
    }
}

function createTooltipPopup(popupData) {
    // Set popup-open state
    document.body.classList.add('popup-open');
    
    // Remove existing popups
    document.querySelectorAll('#userGuidePopup, .modal-overlay').forEach(el => el.remove());

    // Create popup structure
    const popupHTML = `
        <div class="cloneable-container-default" id="userGuidePopup">
            <div class="popup-content">
                <h2 class="popup-header">${popupData.title}</h2>
                <div class="tooltip-body">
                    <h3 class="tooltip-header">${popupData.content.header}</h3>
                    <div class="tooltip-sections"></div>
                </div>
            </div>
        </div>
        <div class="modal-overlay"></div>
    `;

    document.body.insertAdjacentHTML('beforeend', popupHTML);

    // Add sections
    const sectionsContainer = document.querySelector('.tooltip-sections');
    popupData.content.sections.forEach(section => {
        sectionsContainer.innerHTML += `
            <div class="tooltip-section">
                <h4 class="section-title">${section.title}</h4>
                <p class="section-text">${section.text}</p>
            </div>
        `;
    });

    // Close functionality
    document.querySelector('.modal-overlay').addEventListener('click', () => {
        document.body.classList.remove('popup-open');
        document.querySelectorAll('#userGuidePopup, .modal-overlay').forEach(el => el.remove());
    });
}

function setupHelpButton(popupData) {
    const helpButton = document.querySelector('.text-200.bold');
    if (helpButton) {
        helpButton.addEventListener('click', () => {
            document.body.classList.add('popup-open');
            createTooltipPopup(popupData);
        });
    }
}