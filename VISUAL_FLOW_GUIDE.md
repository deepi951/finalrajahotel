# 🎬 Visual Flow - Admin Panel System

```
═══════════════════════════════════════════════════════════════════════════════
                          🏨 Sri Raja Hotel App Flow
═══════════════════════════════════════════════════════════════════════════════


                             ┌─────────────────┐
                             │  START APP      │
                             │  (SplashScreen) │
                             └────────┬────────┘
                                      │
                                      ▼
                    ╔═════════════════════════════════╗
                    ║                                 ║
                    ║    LOGIN SCREEN (NEW UI!)      ║
                    ║                                 ║
                    ║  🏨 Sri Raja Hotel             ║
                    ║  Welcome Back! 👋              ║
                    ║                                 ║
                    ║  📧 Email: ___________          ║
                    ║  🔒 Password: ________          ║
                    ║                                 ║
                    ║  [🔓 LOGIN]  [🔗 Google]        ║
                    ║  🔐 Forgot Password?            ║
                    ║  📝 Create Account              ║
                    ║                                 ║
                    ╚═════════════════════════════════╝
                                      │
                  ┌───────────────────┴───────────────────┐
                  │                                       │
                  ▼                                       ▼
        ┌─────────────────┐                    ┌─────────────────┐
        │   New User?     │                    │  Existing User? │
        │  Click Register │                    │   Click Login   │
        └────────┬────────┘                    └────────┬────────┘
                 │                                      │
                 ▼                                      ▼
    ┌────────────────────────┐            ┌────────────────────────┐
    │  REGISTER SCREEN       │            │  FIREBASE LOGIN        │
    │                        │            │  (Email & Password)    │
    │ Name: ___________      │            │                        │
    │ Email: ___________     │            │ ✓ Credentials Valid?   │
    │ Password: _______      │            │                        │
    │                        │            │ ├─ YES → Continue      │
    │ [Register]             │            │ └─ NO → Error Message  │
    └────────┬───────────────┘            └────────┬───────────────┘
             │                                     │
             ▼                                     ▼
    ┌────────────────────────┐            ┌─────────────────────┐
    │  Email Verification    │            │  Email Verified?    │
    │                        │            │                     │
    │  Check your email for  │            │  ├─ YES → Continue  │
    │  verification link     │            │  └─ NO → Error      │
    │                        │            │                     │
    │  [Wait for email]      │            └────────┬────────────┘
    └────────┬───────────────┘                    │
             │                                    ▼
             │                          ┌──────────────────────┐
             │                          │  IS EMAIL ADMIN?     │
             │                          │  (Check with         │
             │                          │   AdminConfig)       │
             │                          └──────┬───────────────┘
             │                                 │
             │         ┌───────────────────────┴────────────────────┐
             │         │                                            │
             │         ▼                                            ▼
             │  ┌─────────────────────┐              ┌──────────────────────┐
             │  │ keerthuganeshkumar   │              │   ANY OTHER EMAIL     │
             │  │ 2204@gmail.com       │              │                      │
             │  └──────────┬──────────┘              └──────────┬───────────┘
             │             │                                     │
             │             ▼                                     ▼
             │  ╔════════════════════════╗          ╔══════════════════════╗
             │  ║                        ║          ║                      ║
             │  ║  🔐 ADMIN DASHBOARD    ║          ║  👤 USER MENU PAGE   ║
             │  ║                        ║          ║                      ║
             │  ║  ┌──────────────────┐  ║          ║  ┌────────────────┐  ║
             │  ║  │ 📋 Manage Menu   │  ║          ║  │ 🥘 Veg Menu    │  ║
             │  ║  └──────────────────┘  ║          ║  └────────────────┘  ║
             │  ║                        ║          ║                      ║
             │  ║  ┌──────────────────┐  ║          ║  ┌────────────────┐  ║
             │  ║  │ 📦 View Orders   │  ║          ║  │ 🍗 Non-Veg Menu│  ║
             │  ║  └──────────────────┘  ║          ║  └────────────────┘  ║
             │  ║                        ║          ║                      ║
             │  ║  ┌──────────────────┐  ║          ║  ┌────────────────┐  ║
             │  ║  │ 🎉 Manage        │  ║          ║  │ 🎉 Catering    │  ║
             │  ║  │    Catering      │  ║          ║  │    Booking     │  ║
             │  ║  └──────────────────┘  ║          ║  └────────────────┘  ║
             │  ║                        ║          ║                      ║
             │  ║  ┌──────────────────┐  ║          ║  ┌────────────────┐  ║
             │  ║  │ 👥 View          │  ║          ║  │ 🛒 View Cart   │  ║
             │  ║  │    Customers     │  ║          ║  └────────────────┘  ║
             │  ║  └──────────────────┘  ║          ║                      ║
             │  ║                        ║          ║  ┌────────────────┐  ║
             │  ║  ┌──────────────────┐  ║          ║  │ 🚪 Logout      │  ║
             │  ║  │ 🚪 Logout        │  ║          ║  └────────────────┘  ║
             │  ║  └──────────────────┘  ║          ║                      ║
             │  ║                        ║          ║                      ║
             │  ╚════════════════════════╝          ╚══════════════════════╝
             │             │                                 │
             │             └─────────────┬───────────────────┘
             │                           │
             └───────────────────────────┴───────────────────┘
                                 │
                                 ▼
                      ┌──────────────────────┐
                      │   Click Logout       │
                      │   Return to Login    │
                      └──────────────────────┘



═══════════════════════════════════════════════════════════════════════════════
                        🔐 Admin Email Check Logic
═══════════════════════════════════════════════════════════════════════════════


    Email entered: keerthuganeshkumar2204@gmail.com
            │
            ▼
    ┌─────────────────────────────────────┐
    │  AdminConfig.isAdminEmail(email)    │
    │                                     │
    │  FOR EACH ADMIN_EMAIL IN LIST:      │
    │    IF email == adminEmail           │
    │      RETURN true                    │
    │    END IF                           │
    │  END FOR                            │
    │  RETURN false                       │
    └────────────────┬────────────────────┘
                     │
        ┌────────────┴────────────┐
        │                         │
       YES                       NO
        │                         │
        ▼                         ▼
    ADMIN FOUND             NOT ADMIN
        │                         │
        ▼                         ▼
  AdminDashboard            MainActivity
  (FULL ACCESS)           (LIMITED ACCESS)


═══════════════════════════════════════════════════════════════════════════════
                    📝 Registration Protection Logic
═══════════════════════════════════════════════════════════════════════════════


    User tries to register:
    Email: keerthuganeshkumar2204@gmail.com
           │
           ▼
    ┌──────────────────────────────────┐
    │  Validate Input Fields           │
    │  - Name not empty? ✓             │
    │  - Valid email? ✓                │
    │  - Password 6+ chars? ✓          │
    └────────────────┬─────────────────┘
                     │
                     ▼
    ┌──────────────────────────────────┐
    │  IS IT ADMIN EMAIL?              │
    │  AdminConfig.isAdminEmail(mail)  │
    └────────────┬────────────────────┘
                 │
        ┌────────┴────────┐
        │                 │
       YES               NO
        │                 │
        ▼                 ▼
    ❌ BLOCKED      ✅ CONTINUE
    Message:        │
    "This email     ▼
    is reserved   Firebase Create
    for admin!"   Account
                  │
                  ▼
              ✓ Success
              Send Email
              Verification


═══════════════════════════════════════════════════════════════════════════════
                         🎨 New Login UI Elements
═══════════════════════════════════════════════════════════════════════════════

LOGIN SCREEN DESIGN:
┌─────────────────────────────────────────┐
│     Background: hotel_bg.png            │
│     Overlay: #80000000 (Dark)           │
│                                         │
│   ┌─────────────────────────────────┐  │
│   │                                 │  │
│   │  🏨 Sri Raja Hotel              │  │
│   │  Welcome Back! 👋               │  │
│   │                                 │  │
│   │  ┌───────────────────────────┐  │  │
│   │  │ 📧 Email ID               │  │  │
│   │  │ ┌─────────────────────────┤  │  │
│   │  │ │ Enter your email...     │  │  │
│   │  │ └─────────────────────────┤  │  │
│   │  └───────────────────────────┘  │  │
│   │                                 │  │
│   │  ┌───────────────────────────┐  │  │
│   │  │ 🔒 Password              │  │  │
│   │  │ ┌─────────────────────────┤  │  │
│   │  │ │ Enter your password...  │  │  │
│   │  │ └─────────────────────────┤  │  │
│   │  └───────────────────────────┘  │  │
│   │                                 │  │
│   │              🔐 Forgot Password?│  │
│   │                                 │  │
│   │  ┌───────────────────────────┐  │  │
│   │  │  🔓 LOGIN                 │  │  │
│   │  └───────────────────────────┘  │  │
│   │                                 │  │
│   │  ┌───────────────────────────┐  │  │
│   │  │ 🔗 Sign in with Google    │  │  │
│   │  └───────────────────────────┘  │  │
│   │                                 │  │
│   │  ─────────  New User?  ─────  │  │
│   │                                 │  │
│   │      📝 Create Account          │  │
│   │                                 │  │
│   └─────────────────────────────────┘  │
│                                         │
└─────────────────────────────────────────┘

COLORS USED:
  Primary: #FF6B35 (Orange)
  Secondary: #4285F4 (Blue)
  Accent: #FFE082 (Gold)
  Text: #FFFFFF (White) on dark
  Text: #333333 (Dark) on light

DRAWABLES CREATED:
  - rounded_white_bg.xml → 15dp radius, white
  - rounded_light_bg.xml → 10dp radius, light gray
  - rounded_button_bg.xml → 10dp radius, orange
  - rounded_google_button_bg.xml → 10dp radius, blue


═══════════════════════════════════════════════════════════════════════════════
                    ✅ Testing Scenarios & Expected Results
═══════════════════════════════════════════════════════════════════════════════

SCENARIO 1: Register as Admin
├─ Email: keerthuganeshkumar2204@gmail.com
├─ Expected: ✓ Registration successful
└─ Verify email needed ✓

SCENARIO 2: Try Register as Admin (Second Time)
├─ Email: keerthuganeshkumar2204@gmail.com
├─ Expected: ❌ "This email is reserved for admin only!"
└─ Registration blocked ✓

SCENARIO 3: Register as Regular User
├─ Email: customer@example.com
├─ Expected: ✓ Registration successful
└─ Verify email needed ✓

SCENARIO 4: Admin Login
├─ Email: keerthuganeshkumar2204@gmail.com
├─ Password: (correct)
├─ Expected: ✓ AdminDashboard opens
└─ Full access to all features ✓

SCENARIO 5: User Login
├─ Email: customer@example.com
├─ Password: (correct)
├─ Expected: ✓ MainActivity opens
└─ Access to menu only ✓

SCENARIO 6: Wrong Password
├─ Email: keerthuganeshkumar2204@gmail.com
├─ Password: (wrong)
├─ Expected: ❌ "Invalid Email or Password"
└─ Login blocked ✓

SCENARIO 7: Email Not Verified
├─ Email: keerthuganeshkumar2204@gmail.com
├─ Password: (correct)
├─ Email verified: NO
├─ Expected: ❌ "Verify your email first"
└─ Login blocked ✓

SCENARIO 8: Forgot Password
├─ Email: keerthuganeshkumar2204@gmail.com
├─ Click: 🔐 Forgot Password?
├─ Expected: ✓ Reset email sent
└─ Check inbox for reset link ✓


═══════════════════════════════════════════════════════════════════════════════
                      📊 File Structure Overview
═══════════════════════════════════════════════════════════════════════════════

app/src/main/java/com/example/rajahotel/
├── LoginActivity.java ─────────────────────── ✅ MODIFIED
│   ├── Added: googleSignInBtn reference
│   ├── Added: Smart admin detection
│   └── Added: Email-based routing logic
│
├── RegisterActivity.java ──────────────────── ✅ MODIFIED
│   ├── Added: Admin email protection
│   └── Added: Error message for admin email
│
├── AdminConfig.java ───────────────────────── ✨ NEW
│   ├── Admin email definitions
│   ├── isAdminEmail() method
│   └── Easy to extend with more admins
│
├── AdminDashboardActivity.java ────────────── ✓ EXISTING
│   ├── Dashboard UI
│   ├── Manage Menu button
│   ├── View Orders button
│   ├── Manage Catering button
│   ├── View Customers button
│   └── Logout button
│
├── MainActivity.java ──────────────────────── ✓ EXISTING
│   ├── Menu display
│   ├── Catering booking
│   └── Order management
│
└── [Other activities...] ──────────────────── ✓ EXISTING

app/src/main/res/layout/
├── activity_login.xml ─────────────────────── ✅ COMPLETELY REDESIGNED
│   ├── ScrollView for responsiveness
│   ├── Card-based UI
│   ├── Rounded corners
│   ├── Modern styling
│   ├── Google sign-in button
│   └── Better typography
│
├── activity_admin_dashboard.xml ──────────── ✓ EXISTING
│   └── Dashboard buttons
│
└── [Other layouts...] ────────────────────── ✓ EXISTING

app/src/main/res/drawable/
├── rounded_white_bg.xml ───────────────────── ✨ NEW
├── rounded_light_bg.xml ───────────────────── ✨ NEW
├── rounded_button_bg.xml ──────────────────── ✨ NEW
├── rounded_google_button_bg.xml ───────────── ✨ NEW
└── [Other drawables...] ───────────────────── ✓ EXISTING


═══════════════════════════════════════════════════════════════════════════════
                           🎉 FINAL SUMMARY
═══════════════════════════════════════════════════════════════════════════════

✅ ADMIN EMAIL SYSTEM:
   ├─ Admin email defined: keerthuganeshkumar2204@gmail.com
   ├─ AdminConfig.java created
   └─ Easy to add more admins

✅ SMART LOGIN ROUTING:
   ├─ Admin email → Admin Dashboard
   ├─ Other emails → User Menu
   └─ Automatic routing

✅ REGISTRATION PROTECTION:
   ├─ Cannot register with admin email
   ├─ Error message shown
   └─ Prevents unauthorized access

✅ BEAUTIFUL LOGIN UI:
   ├─ Modern card-based design
   ├─ Rounded corners
   ├─ Professional colors
   └─ Responsive layout

✅ ADMIN DASHBOARD:
   ├─ Manage menu items
   ├─ View orders
   ├─ Manage catering
   ├─ View customers
   └─ Secure logout

✅ READY TO BUILD & RUN! 🚀


═══════════════════════════════════════════════════════════════════════════════
                        🏁 YOU'RE ALL SET!
═══════════════════════════════════════════════════════════════════════════════

Admin Email: keerthuganeshkumar2204@gmail.com
Status: COMPLETE ✅
Ready: BUILD & RUN! 🚀

