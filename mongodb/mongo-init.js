db.createUser(
    {
        user: "user2412",
        pwd: "abcde4321",
        roles: [
            {
                role: "readWrite",
                db: "sodium-consumption-calc"
            }
        ]
    }
)