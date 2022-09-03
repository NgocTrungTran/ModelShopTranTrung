class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

class User {
    constructor(id, fullName, username, password, email, phone, coin, avatar, blocked, locationRegion) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.coin = coin;
        this.avatar = avatar;
        this.blocked = blocked;
        this.locationRegion = locationRegion;
    }
}

class Deposit {
    constructor(id, userId, createBy, transactionAmount) {
        this.id = id;
        this.userId = userId;
        this.createBy = createBy;
        this.transactionAmount = transactionAmount;
    }
}

class Product {
    constructor(id, title, price, image, category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.category = category;
    }
}

class Category {
    constructor(id, title) {
        this.id = id;
        this.title = title;
    }
}