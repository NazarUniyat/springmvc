class DynamicForm {
    constructor(config) {
        this.addFormSubmitListener(config);
    }

    encodeQueryData (data) {
        let ret = [];
        for (let d in data) {
            if (data[d] !== '') {
                ret.push(encodeURIComponent(d) + '=' + encodeURIComponent(data[d]));
            }
        }
        return ret.join('&');
    }

    addFormSubmitListener (config) {
        // Getting data from config object
        let { formValues, apiEndpoint, responseProps, formID, tableID } = config;

        this.form = document.getElementById(formID);
        this.tableBody = document.getElementById(tableID);

        this.form.addEventListener('submit', (event) => {
            event.preventDefault();

            const params = {};
            for (let key in formValues) {
                params[key] = this.form.elements[formValues[key]].value
            }

            const request = `${apiEndpoint}?${this.encodeQueryData(params)}`;

            fetch(request).then(response => response.json())
                // Working with admin array
                .then(adminArray => {
                    // removing everyting from list (all previous data)
                    while (this.tableBody.firstChild) {
                        this.tableBody.removeChild(this.tableBody.firstChild);
                    }

                    adminArray.forEach(admin => {
                        /**
                         * Here we have object which is admin
                         * {
                         *      name: 'Nazar',
                         *      age: 21
                         * }
                         */
                        let tr = document.createElement('tr');

                        responseProps.forEach(item => {
                            let td = document.createElement('td');
                            td.innerText = _.get(admin, item); // admin.name ... admin.age
                            tr.appendChild(td);
                        });

                        this.tableBody.appendChild(tr);
                    })
                })
                .catch(err => {
                    console.log('Error', err);
                });
        });
    }
};
