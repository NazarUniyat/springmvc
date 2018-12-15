const placesConfig = {
    formID: 'places-form',
    templateBlockID: 'places-list',
    formValues: {
        specification: 'specification',
        name: 'name',
        city: 'city'
    },
    apiEndpoint: '/api/places/all',
    templateFunction: responseItem => {
        const getStringNODE = item => {
            const {
                img,
                name,
                specification,
                about,
                placeId
            } = item;

            return `
                <div class="row border p-3 rounded m-3">
                    <div class="col-md-5">
                        <img src="${img}" class="img-fluid rounded">
                    </div>
                    <div class="col-md-7">
                        <h4>${name}</h4>
                        <h3>${specification}</h3>
                        <p>${about}</p>
                        <a href="/places/${placeId}" class="btn btn-primary">More</a>
                    </div>
                </div>
            `;
        }

        return new DOMParser().parseFromString(getStringNODE(responseItem), 'text/html').firstChild;
    }
};

const dynamicEventsLoader = new DynamicForm(placesConfig);
