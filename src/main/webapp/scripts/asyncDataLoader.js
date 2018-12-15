class DynamicForm {
    constructor(config) {
        this.addFormSubmitListener(config);
    }

    static encodeQueryData (data) {
        let ret = [];

        for (let d in data) {
            if (data[d] !== '') {
                ret.push(encodeURIComponent(d) + '=' + encodeURIComponent(data[d]));
            }
        }

        return ret.join('&');
    }

    addFormSubmitListener (config) {
        let {
            formValues,
            apiEndpoint,
            formID,
            templateBlockID,
            templateFunction
        } = config;

        this.form = document.getElementById(formID);
        this.templateBlock = document.getElementById(templateBlockID);

        this.form.addEventListener('submit', (event) => {
            event.preventDefault();

            const params = {};

            for (let key in formValues) {
                params[key] = this.form.elements[formValues[key]].value
            }

            const request = `${apiEndpoint}?${DynamicForm.encodeQueryData(params)}`;

            fetch(request).then(response => response.json())
                .then(adminArray => {
                    while (this.templateBlock.firstChild) {
                        this.templateBlock.removeChild(this.templateBlock.firstChild);
                    }

                    adminArray.forEach(admin => {
                        this.templateBlock.appendChild(templateFunction(admin));
                    });
                })
                .catch(err => {
                    console.log('Error', err);
                });
        });
    }
};
