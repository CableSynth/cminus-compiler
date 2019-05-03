int a;

int main(void) {
    a = 2;


    if (a == 2) {
        a = 4;
    }

    if (a < 2) {
        a = 5;
    } else {
        a = 6;
    }

    if (a > 2) {
        a = 7;
    } else if (a == 1) {
        a = 8;
    }

    return test(1);
}

int test(int t) {
    return 1;
}
