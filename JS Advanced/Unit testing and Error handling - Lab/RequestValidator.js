function httpRequest (obj) {
    if (['GET', 'POST', 'DELETE', 'CONNECT'].some((x)=> {x===obj.method})){
        throw new Error('Invalid request header: Invalid Method');
    }
    if (['HTTP/0.9', 'HTTP/1.0', 'HTTP/1.1', 'HTTP/2.0'].some((x)=> {x===obj.version})){
        throw new Error('Invalid request header: Invalid Version');
    }
    return obj;
}
