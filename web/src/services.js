const hostAddress = ''
export const checkSession = () => {
    return fetch(hostAddress + '/api/auth', {
        method: 'GET',
    })
    .catch(() => {
        return Promise.reject({error: 'network-error'})
    })
    .then(convertError)
};

export const createSession = (username, password) => {
    return fetch(hostAddress + '/api/auth',  {
      method: 'POST',
      headers: new Headers({
        'content-type': 'application/json',
      }),
      body: JSON.stringify({ username, password }),
    })
    .catch( () => Promise.reject({ error: 'network-error'} ) )
    .then(convertError);
  };

export const endSession = () => {
    return fetch(hostAddress + '/api/auth',  {
      method: 'DELETE',
    })
    .catch( () => Promise.reject({ error: 'network-error'} ) )
    .then(convertError);
};

export const dump = () => {
    return fetch(hostAddress + '/api/url/dump', {
        method: 'GET',
    })
    .catch(() => {
        return Promise.reject({error: 'network-error'})
    })
    .then(convertError)
};

export const postUrl = (longUrl) => {
    return fetch(hostAddress + '/api/url', {
        method: 'POST',
        headers: new Headers ({
            'content-type': 'application/json',
        }),
        body: JSON.stringify({longUrl}),
    })
    .catch(() => Promise.reject({error: 'network-error'}))
    .then(convertError);
};


export const getUrl = (data) => {
    return fetch(`/api/url?shortUrl=${data}`, {
        method: 'GET',
    })
    .catch(() => Promise.reject({error: 'network-error'}))
    .then(convertError);
};



function convertError(response) {
    if(response.ok) {
      return response.json();
    }
    return response.json().then(error => Promise.reject(error));
  };