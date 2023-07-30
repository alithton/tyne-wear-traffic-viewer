/*
 * Convert an instance of the FormData created by the browser's built-in FormData
 * API to an object.
 */
export function formDataToObject(formData) {
    const formObj = {};
    formData.forEach((value, key) => formObj[key] = value);
    return formObj;
}