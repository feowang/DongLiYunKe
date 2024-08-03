import axios from "axios";

export const doPost = (url, data) => {
    return axios({
        method: 'post',
        url: url,
        data: data,
    })
}