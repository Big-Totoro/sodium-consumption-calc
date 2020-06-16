db.createUser(
    {
        user: "sskuratov",
        pwd: "Powell2100!",
        roles: [
            {
                role: "readWrite",
                db: "sodium-consumption-calc"
            }
        ]
    }
)