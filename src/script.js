let Categories = [
    {
        id : "C1",
        categoryName : "Platters",
        superCategory : {
    
            superCategoryName : "South Indian",
            id : "SC1"
        }
    },
    
    {
        id : "C2",
        categoryName : "Tandoor",
        superCategory : {
    
            superCategoryName : "North Indian",
            id : "SC2"
        }
    },
    
    {
        id : "C3",
        categoryName : "Dosa",
        superCategory : {
    
            superCategoryName : "South Indian",
            id : "SC3"
        }
    },
    
    {
        id : "C4",
        categoryName : "Vegetables",
        superCategory : {
    
            superCategoryName : "North Indian",
            id : "SC4"
        }
    }
    
    ]
    
    
var items = [
    {
        id : "item1",
        itemName : "Butter Roti",
        rate : 20,
        taxes : [
            {
                name : "Service Charge",
                rate : 10,
                isInPercent : 'Y'
            },
            {
                name : "GST",
                rate : 18,
                isInPercent : 'Y'
            },

        ],
        category : {
            categoryId : "C2"
        }

    },

    {
        id : "item2",
        itemName : "Paneer Butter Masala",
        rate : 120,
        taxes : [
            {
                name : "Service Charge",
                rate : 10,
                isInPercent : 'Y'
            },
            {
                name : "GST",
                rate : 18,
                isInPercent : 'Y'
            },
            {
                name : "Service Tax",
                rate : 10,
                isInPercent : 'Y'
            },
            


        ],
        category : {
            categoryId : "C4"
        }

    },

    {
        id : "item3",
        itemName : "Masala Dosa",
        rate : 50,
        taxes : [
            {
                name : "GST",
                rate : 18,
                isInPercent : 'Y'
            },
            {
                name : "Service Tax",
                rate : 10,
                isInPercent : 'Y'
            },
            


        ],
        category : {
            categoryId : "C3"
        }

    },

    {
        id : "item4",
        itemName : "Dosa Platter",
        rate : 150,
        taxes : [
            
            {
                name : "Service Tax",
                rate : 10,
                isInPercent : 'Y'
            },

        ],
        category : {
            categoryId : "C1"
        }

    },
            

]
var Bill = {
    id : "B1",
    billNumber : 1,
    opentime : "06 Nov 2020 14:19",
    customerName : "CodeQuotient",
    billItems : [
        {
            id : "item2",
            quantity : 3,
            discount : {
                rate : 10,
                isInPercent : 'Y'
            }

        },
        {
            id : "item1",
            quantity : 9,
            discount : {
                rate : 10,
                isInPercent : 'Y'
            }

        },
        {
            id : "item4",
            quantity : 2,
            discount : {
                rate : 15,
                isInPercent : 'Y'
            }

        }
    ]
}
    
    function calc_bill(){
        var totalAmount = 0
        Bill.billItems.forEach(function(billData) {
            var taxPercent = 0, discountPercent = 0, price = 0
            var quantity = billData.quantity
            var discount = billData.discount
            delete billData.discount
            delete billData.quantity
            items.forEach(function(itemsData) {
                if(itemsData.id === billData.id) {
                    billData.name = itemsData.itemName
                    billData.quantity = quantity 
                    billData.discount = discount
                    discountPercent = billData.discount.rate
                    billData.taxes = itemsData.taxes
                    billData.taxes.forEach(function(taxData) {
                        taxPercent += taxData.rate
                    })
                    price = itemsData.rate * quantity
                    price += ((taxPercent * price) / 100)
                    price -= ((discountPercent * price) / 100)
                    billData.amount = price
                    totalAmount += price
                    Categories.forEach(function(catergoryData) {
                        if(catergoryData.id === itemsData.category.categoryId) {
                            billData.superCategoryName = catergoryData.superCategory.superCategoryName
                            billData.categoryName = catergoryData.categoryName
                        }
                    })
                }
            })
        })
        Bill["Total Amount"] = totalAmount
    }
    calc_bill();
    let printBill = JSON.stringify(Bill) // Converted object to string. 
    document.getElementById("bill").innerHTML = printBill; // to print in div