import http from '@/http'

const getCpuLoad = async () => {
    return await http.get('/metrics/cpu')
}


export {
    getCpuLoad
}