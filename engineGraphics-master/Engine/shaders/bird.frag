#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
	vec2 time;
} fs_in;

uniform sampler2D tex;

void main()
{
	color += texture(tex, fs_in.tc);
    color += texture(tex, fs_in.tc + vec2(0.005f,0.005f));
    color += texture(tex, fs_in.tc + vec2(0.005f,-0.005f));
    color += texture(tex, fs_in.tc + vec2(-0.005f,-0.005f));
    color += texture(tex, fs_in.tc + vec2(-0.005f,0.005f));
    color = color / 5.0f;

    color += vec4(1.0 * fs_in.time.x, 0.3 * fs_in.time.x,0.3 * fs_in.time.x,0.0f);
    if (color.w < 0.1) {
        discard;
    }
}