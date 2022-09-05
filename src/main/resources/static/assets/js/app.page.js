class App {
    static DOMAIN = location.origin;

    static BASE_URL = this.DOMAIN + "/api"
    static BASE_URL_USER = this.BASE_URL + "/users"
    static BASE_URL_USER_TRASH = this.BASE_URL_USER + "/trash"

    static BASE_URL_PRODUCT = this.BASE_URL + "/products";
    static BASE_URL_PRODUCT_TRASH = this.BASE_URL_PRODUCT + "/trash";

    static BASE_URL_CLOUD_IMAGE = "https://res.cloudinary.com/trantrung/image/upload";
    static BASE_URL_CLOUD_VIDEO = "https://res.cloudinary.com/trantrung/video/upload";
    static BASE_SCALE_IMAGE = "c_limit,w_150,h_100,q_100";

    static ERROR_400 = "The operation failed, please check the data again.";
    static ERROR_403 = "Access Denied! You are not authorized to perform this function.";
    static ERROR_404 = "This content has been removed or does not exist";
    static ERROR_500 = "Data saving failed, please contact the system administrator.";
    static SUCCESS_CREATED = "Successful data generation !";
    static SUCCESS_UPDATED = "Data update successful !";
    static SUCCESS_DELETED = "Delete product successfully !";

    static showDeleteConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure to delete the selected product ?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, please delete this client !',
            cancelButtonText: 'Cancel',
        })
    }

    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500
        })
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            text: t,
        })
    }
}
