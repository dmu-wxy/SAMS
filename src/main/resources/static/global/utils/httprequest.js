function request(url, method = 'GET', data, type) {
    const formData = new FormData();
    for (let key in data) {
        formData.append(key, data[key])
    }
    return fetch(url, {
        method,
        body: formData
    })
}


